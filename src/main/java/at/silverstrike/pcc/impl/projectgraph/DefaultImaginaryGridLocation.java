/**
 * This file is part of Project Control Center (PCC).
 * 
 * PCC (Project Control Center) project is intellectual property of 
 * Dmitri Anatol'evich Pisarenko.
 * 
 * Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
 * All rights reserved
 *
 **/

package at.silverstrike.pcc.impl.projectgraph;

import at.silverstrike.pcc.api.projectgraph.ImaginaryGridLocation;

/**
 * @author DP118M
 *
 */
class DefaultImaginaryGridLocation implements ImaginaryGridLocation {
    private int x;
    private int y;
    
    @Override
    public void setX(int aX) {
        this.x = aX;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setY(int aY) {
        this.y = aY;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
