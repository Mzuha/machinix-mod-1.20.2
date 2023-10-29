package com.mzuha.screen.renderer

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.util.math.Rect2i
import team.reborn.energy.api.EnergyStorage


/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense" (FORGE VERSION)
 *  Details can be found in the license file in the root folder of this project
 */
class EnergyInfoArea(
    xMin: Int,
    yMin: Int,
    private val energy: EnergyStorage,
    width: Int = 8,
    height: Int = 64
) {
    private val area = Rect2i(xMin, yMin, width, height)

    fun draw(context: DrawContext) {
        val height = area.height
        val stored = (height * (energy.amount / energy.capacity.toFloat())).toInt()
        context.fillGradient(
            area.x, area.y + (height - stored),
            area.x + area.width, area.y + area.height,
            -0x4aeb00, -0x9ff500
        )
    }
}