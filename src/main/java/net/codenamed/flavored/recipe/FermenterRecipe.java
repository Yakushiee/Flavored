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

public class FermenterRecipe implements Recipe<SimpleInventory> {
    private  final ItemStack output;
    private  final List<Ingredient> recipeItems;

    public FermenterRecipe(List<Ingredient> ings, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ings;
    }
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }
        // Only fermenting and liquid slot

        if (recipeItems.size() == 2) {
            if(recipeItems.get(0).test(inventory.getStack(2)) && recipeItems.get(1).test(inventory.getStack(3))) {
                return  true;
            }
        }
        // Only liquid slot
        else if (recipeItems.size() == 1) {
            if(recipeItems.get(0).test(inventory.getStack(0))) {
                return  true;
            }

            }
        // Ingredient, liquid and fermenting slot
        else if (recipeItems.size() == 3) {
        if (recipeItems.get(0).test(inventory.getStack(0))
                    && recipeItems.get(1).test(inventory.getStack(2))
                    && recipeItems.get(2).test(inventory.getStack(3))) {


                return true;

            }
        }
        // Every slot
        else if (recipeItems.size() == 4) {
                if (recipeItems.get(0).test(inventory.getStack(0))
                        && recipeItems.get(1).test(inventory.getStack(2))
                        && recipeItems.get(2).test(inventory.getStack(3))
                        && recipeItems.get(3).test(inventory.getStack(4))) {


                    return true;
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

    public  static  class Type implements RecipeType<FermenterRecipe> {
        public  static  final Type INSTANCE = new Type();
        public  static  final String ID = "fermenting";
    }


    public  static  class Serializer implements RecipeSerializer<FermenterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "fermenting";

        public static final Codec<FermenterRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredients").forGetter(FermenterRecipe::getIngredients),
                RecipeCodecs.CRAFTING_RESULT.fieldOf("output").forGetter(r -> r.output)
        ).apply(in, FermenterRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }
        @Override
        public Codec<FermenterRecipe> codec() {
            return CODEC;
        }

        @Override
        public FermenterRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new FermenterRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, FermenterRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getResult(null));
        }
    }




}
