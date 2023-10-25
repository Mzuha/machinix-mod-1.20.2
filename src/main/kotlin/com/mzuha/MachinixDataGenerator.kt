package com.mzuha

import com.mzuha.datagen.ModBlockTagProvider
import com.mzuha.datagen.ModItemTagProvider
import com.mzuha.datagen.ModLootTableProvider
import com.mzuha.datagen.ModModelProvider
import com.mzuha.datagen.ModRecipeProvider
import java.util.concurrent.CompletableFuture
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.registry.RegistryWrapper.WrapperLookup

class MachinixDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        val pack = fabricDataGenerator.createPack()
        pack.addProvider { output: FabricDataOutput, completableFuture: CompletableFuture<WrapperLookup> ->
            ModBlockTagProvider(
                output, completableFuture
            )
        }
        pack.addProvider { output: FabricDataOutput, completableFuture: CompletableFuture<WrapperLookup> ->
            ModItemTagProvider(
                output,
                completableFuture
            )
        }
        pack.addProvider { dataOutput: FabricDataOutput ->
            ModLootTableProvider(
                dataOutput
            )
        }
        pack.addProvider { output: FabricDataOutput ->
            ModModelProvider(
                output
            )
        }
        pack.addProvider { output: FabricDataOutput ->
            ModRecipeProvider(
                output
            )
        }
    }
}