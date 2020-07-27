package com.shinoow.abyssalcraft.api.dimension;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

/**
 * Class holding dimension-related data for portals
 * @author shinoow
 *
 * @since 2.0.0
 */
public class DimensionData {

	private int r, g, b;
	private int id, gatewayKey;
	private Set<Integer> connectedDimensions;
	private Class<? extends EntityLiving> mobClass;
	private ResourceLocation overlay;

	public DimensionData() {}

	private DimensionData(Builder builder) {
		this.id = builder.id;
		this.r = builder.r;
		this.g = builder.g;
		this.b = builder.b;
		this.gatewayKey = builder.gatewayKey;
		this.connectedDimensions = builder.connectedDimensions;
		this.mobClass = builder.mobClass;
		this.overlay = builder.overlay;
	}
	
	/**
	 * Returns the red segment of the color
	 */
	public int getR() {
		return r;
	}

	/**
	 * Returns the green segment of the color
	 */
	public int getG() {
		return g;
	}

	/**
	 * Returns the blue segment of the color
	 */
	public int getB() {
		return b;
	}
	
	/**
	 * Returns the dimension ID associated 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the required Gateway Key
	 * <ul>
	 * <li>0 = Gateway Key</li>
	 * <li>1 = Asorah's Dreaded Gateway Key</li>
	 * <li>1 = Cha'garoth's R'lyehian Gateway Key</li>
	 * <li>3 = The Silver Key</li>
	 * </ul>
	 */
	public int getGatewayKey() {
		return gatewayKey;
	}
	
	/**
	 * Returns the dimensions this dimension is connected to<br>
	 * If two dimensions are connected, you can make a portal in one going to the other
	 */
	public Set<Integer> getConnectedDimensions() {
		return connectedDimensions;
	}
	
	/**
	 * Returns the class of the mob the portal can spawn at times
	 */
	@Nullable
	public Class<? extends EntityLiving> getMobClass() {
		return mobClass;
	}
	
	/**
	 * Returns the overlay texture for the portal, if any
	 */
	@Nullable
	public ResourceLocation getOverlay() {
		return overlay;
	}
	
	public static class Builder {
		
		private int r, g, b;

		private int id, gatewayKey;
		private Set<Integer> connectedDimensions = new HashSet<>();
		private Class<? extends EntityLiving> mobClass;
		private ResourceLocation overlay;
		
		public Builder(int id) {
			this.id = id;
			this.r = 1;
			this.g = 1;
			this.b = 1;
		}
		
		/**
		 * Sets the portal color
		 * @param r Red color
		 * @param g Green color
		 * @param b Blue color
		 */
		public Builder setColor(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
			return this;
		}
		
		/**
		 * Sets the required gateway key
		 * <ul>
		 * <li>0 = Gateway Key</li>
		 * <li>1 = Asorah's Dreaded Gateway Key</li>
		 * <li>1 = Cha'garoth's R'lyehian Gateway Key</li>
		 * <li>3 = The Silver Key</li>
		 * </ul>
		 * @param gatewayKey Integer representing the key in question
		 */
		public Builder setGatewayKey(int gatewayKey) {
			this.gatewayKey = gatewayKey;
			return this;
		}
		
		/**
		 * Adds a connected dimension<br>
		 * If two dimensions are connected, you can make a portal in one going to the other
		 * If none are added, the dimension isn't restricted
		 * @param id ID of the dimension to add
		 */
		public Builder addConnectedDimension(int id) {
			connectedDimensions.add(id);
			return this;
		}
		
		/**
		 * Sets the mob the portal for this dimension can spawn at times
		 * @param mobClass Class of the mob
		 */
		public Builder setMob(Class<? extends EntityLiving> mobClass) {
			this.mobClass = mobClass;
			return this;
		}
		
		/**
		 * Sets an overlay texture for the portal
		 * @param overlay Resource Location pointing to the texture
		 */
		public Builder setOverlay(ResourceLocation overlay) {
			this.overlay = overlay;
			return this;
		}
		
		/**
		 * Builds the Dimension Data
		 */
		public DimensionData build() {
			DimensionData data = new DimensionData(this);

			return data;
		}
	}
}