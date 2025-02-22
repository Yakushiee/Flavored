package net.codenamed.flavored.recipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class BoilerRecipe implements Recipe<SimpleInventory> {
    private  final ItemStack output;
    private  final List<Ingredient> recipeItems;

    public BoilerRecipe(List<Ingredient> ings, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ings;
    }
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }


        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (j == i) continue;
                for (int k = 0; k <= 4; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 0; l <= 4; l++) {
                        if (l == i || l == j || l == k) continue;
                        boolean result = false;
                        int size = 0;

                        for (int x = 0; x < inventory.size(); x++) {
                            if (!inventory.getStack(x).isEmpty()) {
                                size++;
                            }
                        }

                        if (recipeItems.size() == 4 && size == 5) {
                            result = recipeItems.get(0).test(inventory.getStack(i))
                                    && recipeItems.get(1).test(inventory.getStack(j))
                                    && recipeItems.get(2).test(inventory.getStack(k))
                                    && recipeItems.get(3).test(inventory.getStack(l));
                        }
                        if (recipeItems.size() == 3 && size == 4) {
                            result = recipeItems.get(0).test(inventory.getStack(i))
                                    && recipeItems.get(1).test(inventory.getStack(j))
                                    && recipeItems.get(2).test(inventory.getStack(k));
                        }
                        else if (recipeItems.size() == 2 && size == 3) {
                            result = recipeItems.get(0).test(inventory.getStack(i))
                                    && recipeItems.get(1).test(inventory.getStack(j));
                        }
                        else if (recipeItems.size() == 1 && size == 2) {
                            result = recipeItems.get(0).test(inventory.getStack(i));
                        }


                        if (result) {
                            return  true;
                        }
                    }
                }
            }
        }
        return  false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient>list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public  static  class Type implements RecipeType<BoilerRecipe> {
        public  static  final Type INSTANCE = new Type();
        public  static  final String ID = "boiling";
    }


    public  static  class Serializer implements RecipeSerializer<BoilerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "boiling";

        public static final Codec<BoilerRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredients").forGetter(BoilerRecipe::getIngredients),
                RecipeCodecs.CRAFTING_RESULT.fieldOf("output").forGetter(r -> r.output)
        ).apply(in, BoilerRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }
        @Override
        public Codec<BoilerRecipe> codec() {
            return CODEC;
        }

        @Override
        public BoilerRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new BoilerRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, BoilerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getResult(null));
        }
    }




}