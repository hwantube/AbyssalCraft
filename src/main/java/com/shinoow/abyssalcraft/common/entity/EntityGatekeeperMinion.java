/**
 * AbyssalCraft
 * Copyright 2012-2015 Shinoow
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shinoow.abyssalcraft.common.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.shinoow.abyssalcraft.AbyssalCraft;
import com.shinoow.abyssalcraft.api.entity.IAntiEntity;
import com.shinoow.abyssalcraft.api.entity.ICoraliumEntity;
import com.shinoow.abyssalcraft.api.entity.IDreadEntity;

public class EntityGatekeeperMinion extends EntityMob implements ICoraliumEntity, IDreadEntity, IAntiEntity {

	public EntityGatekeeperMinion(World par1World) {
		super(par1World);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.35D, false));
		tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.35D));
		tasks.addTask(4, new EntityAIWander(this, 0.35D));
		tasks.addTask(7, new EntityAILookIdle(this));
		tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		setSize(1.4F, 2.8F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(250.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.2D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.699D);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(18.0D);
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		swingItem();
		boolean flag = super.attackEntityAsMob(par1Entity);

		return flag;
	}

	@Override
	protected String getDeathSound()
	{
		return "abyssalcraft:shadow.death";
	}

	@Override
	protected void func_145780_a(int par1, int par2, int par3, Block par4)
	{
		playSound("mob.spider.step", 0.15F, 1.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if(rand.nextInt(10) == 0){
			List<EntityRemnant> remnants = worldObj.getEntitiesWithinAABB(EntityRemnant.class, boundingBox.expand(16D, 16D, 16D));
			if(remnants != null){
				Iterator<EntityRemnant> iter = remnants.iterator();
				while(iter.hasNext())
					iter.next().enrage(false);
			}
			worldObj.playSoundAtEntity(this, "abyssalcraft:remnant.scream", 3F, 1F);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	@Override
	protected Item getDropItem()
	{
		return AbyssalCraft.ethaxiumIngot;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}
}