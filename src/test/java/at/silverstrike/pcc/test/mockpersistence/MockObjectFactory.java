/**
 * This file is part of Project Control Center (PCC).
 * 
 * Project Control Center (PCC) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Project Control Center (PCC) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2010 Dmitri Anatol'evich Pisarenko
 **/

package at.silverstrike.pcc.test.mockpersistence;

import at.silverstrike.pcc.api.model.ControlProcess;
import at.silverstrike.pcc.api.model.Resource;
import at.silverstrike.pcc.api.model.ResourceAllocation;
import at.silverstrike.pcc.api.persistence.Persistence;

public class MockObjectFactory {
    public ControlProcess createControlProcess(final Long anId)
    {
        final MockControlProcess returnValue = new MockControlProcess();
        
        returnValue.setId(anId);
        
        return returnValue;
    }
    
    public Persistence createPersistence()
    {
        return new MockPersistence();
    }
    
    public Resource createResource(final Long anId)
    {
        final MockResource returnValue = new MockResource();
        
        returnValue.setId(anId);
        
        return returnValue;
    }
    
    public ResourceAllocation createResourceAllocation()
    {
        return new MockResourceAllocation();
    }
    
}
