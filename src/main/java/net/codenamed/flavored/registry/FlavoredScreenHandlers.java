package net.codenamed.flavored.registry;

import net.codenamed.flavored.screen.BoilerScreenHandler;
import net.codenamed.flavored.screen.FermenterScreenHandler;
import net.codenamed.flavored.screen.OvenScreenHandler;
import net.codenamed.flavored.screen.RangeScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.codenamed.flavored.Flavored;

public class FlavoredScreenHandlers {

    public static final ScreenHandlerType<BoilerScreenHandler> BOILER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Flavored.MOD_ID, "boiling"),
                    new ExtendedScreenHandlerType<>(BoilerScreenHandler::new));
    public static final ScreenHandlerType<FermenterScreenHandler> FERMENTER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(Flavored.MOD_ID, "fermenting"),
                    new ExtendedScreenHandlerType<>(FermenterScreenHandler::new));

    public static ScreenHandlerType<OvenScreenHandler> OVEN_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(Flavored.MOD_ID, "baking"),
                    OvenScreenHandler::new);

    public static ScreenHandlerType<RangeScreenHandler> RANGE_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(Flavored.MOD_ID, "frying"),
                    RangeScreenHandler::new);




    public static void registerScreenHandlers() {
        Flavored.LOGGER.info("Registering Screen Handlers for " + Flavored.MOD_ID);
    }
}