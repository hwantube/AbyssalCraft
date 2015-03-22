package com.shinoow.abyssalcraft.common.blocks;

import java.util.Random;

import com.shinoow.abyssalcraft.common.structures.StructureHouse;
import com.shinoow.abyssalcraft.common.util.EntityUtil;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockHouse extends Block {

	Random rand;

	public BlockHouse() {
		super(Material.wood);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
		setBlockTextureName("abyssalcraft:Crate");
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(EntityUtil.isPlayerCoralium(par5EntityPlayer)){
			if(par1World.isRemote)
				FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Whoppidy-doo, a house."));
			if(!par1World.isRemote){
				StructureHouse house = new StructureHouse();
				house.generate(par1World, rand, par2, par3, par4);
				par1World.getChunkFromBlockCoords(par2, par4).setChunkModified();
			}
		} else{
			if(!par1World.isRemote)
				par1World.setBlock(par2, par3, par4, Blocks.dirt);
			if(par1World.isRemote)
				FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(StatCollector.translateToLocal(Blocks.dirt.getUnlocalizedName() + ".name")));

		}
		return false;
	}
}