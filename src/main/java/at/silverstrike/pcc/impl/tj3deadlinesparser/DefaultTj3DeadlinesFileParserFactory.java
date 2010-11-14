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

package at.silverstrike.pcc.impl.tj3deadlinesparser;

import at.silverstrike.pcc.api.jruby.AbstractRubyImplementationFactory;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParser;
import at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParserFactory;

/**
 * @author Dmitri Pisarenko
 * 
 */
public class DefaultTj3DeadlinesFileParserFactory extends
        AbstractRubyImplementationFactory<Tj3DeadlinesFileParser> implements
        Tj3DeadlinesFileParserFactory {

    @Override
    protected String getInterfacename() {
        return "tj3deadlinesparser";
    }

    @Override
    protected String getModuleName() {
        return "Tj3DeadlinesFileParser";
    }
}
