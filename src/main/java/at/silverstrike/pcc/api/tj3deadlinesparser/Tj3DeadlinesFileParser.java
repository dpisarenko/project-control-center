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

package at.silverstrike.pcc.api.tj3deadlinesparser;

import java.util.List;

import at.silverstrike.pcc.api.conventions.ModuleWithInjectableDependencies;
import at.silverstrike.pcc.api.conventions.PccException;
import at.silverstrike.pcc.api.conventions.SingleActivityModule;

/**
 * Interface of an object for reading and parsing CSV files with process end
 * times. These files are generated by TaskJuggler III and a sample file can be
 * found in pcc\doc\2010_05_28_prototype1\ref\deadlines.csv.
 * 
 * The file consists of lines with two pieces of data - task ID and its end
 * time.
 * 
 * Note that in the file the process IDs are given as "Tx" where x is the
 * process ID. So, when parsing the file, we should strip the T.
 * 
 * @author Dmitri Pisarenko
 */
public interface Tj3DeadlinesFileParser extends
        ModuleWithInjectableDependencies, SingleActivityModule {
    /**
     * @param Input
     *            stream with CSV deadline file.
     */
    void setInputFileName(final String anInputFileName);

    /**
     * Parses the file (given via setInputStream method) and saves the result in
     * processEndTimes property.
     */
    void run() throws PccException;

    /**
     * @return Process end time information contained in the CSV deadline file.
     */
    List<ProcessEndTimeTuple> getProcessEndTimes();
}
