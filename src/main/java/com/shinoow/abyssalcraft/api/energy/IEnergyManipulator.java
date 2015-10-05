/*******************************************************************************
 * AbyssalCraft
 * Copyright (c) 2012 - 2015 Shinoow.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Contributors:
 *     Shinoow -  implementation
 ******************************************************************************/
package com.shinoow.abyssalcraft.api.energy;

/**
 * Interface to use on blocks that can manipulate Ley Lines (WIP)<br>
 * WARNING: Any methods in this interface might end up getting removed,<br>
 * so I would suggest not using it until this text (and the WIP part) is removed.
 * 
 * @author shinoow
 * 
 * @since 1.4.5
 */
public interface IEnergyManipulator {

	/**
	 * Gets the speed at which the block can drain Potential Energy
	 */
	public int drainSpeed();

	/**
	 * Gets the quanta of Potential Energy that the block can drain
	 */
	public float energyQuanta();

	/**
	 * Returns the stability (the higher, the smaller chance of bad things happening)
	 */
	public int stability();
}