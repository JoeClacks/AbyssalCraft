/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2017 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.integration.jei.upgrades;

import javax.annotation.Nonnull;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import com.shinoow.abyssalcraft.integration.jei.AbyssalCraftRecipeCategoryUid;

public class UpgradeRecipeCategory implements IRecipeCategory {

	private static final int input = 0, kit = 1, output = 2;

	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final IDrawable slotDrawable;
	@Nonnull
	private final String localizedName;

	public UpgradeRecipeCategory(IGuiHelper guiHelper){

		ResourceLocation location = new ResourceLocation("textures/gui/container/anvil.png");
		background = guiHelper.createDrawable(location,  15, 46, 146, 26);

		localizedName = I18n.translateToLocal("container.abyssalcraft.upgrade.nei");

		slotDrawable = guiHelper.getSlotDrawable();
	}

	@Override
	public String getUid() {

		return AbyssalCraftRecipeCategoryUid.UPGRADE;
	}

	@Override
	public String getTitle() {

		return localizedName;
	}

	@Override
	public IDrawable getBackground() {

		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(recipeWrapper instanceof UpgradeRecipeWrapper){
			IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();

			itemStacks.init(input, true, 11, 0);
			itemStacks.init(kit, true, 60, 0);
			itemStacks.init(output, false, 118, 0);

			itemStacks.set(ingredients);
		}
	}

	@Override
	public IDrawable getIcon() {

		return null;
	}
}
