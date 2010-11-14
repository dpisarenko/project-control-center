#
# This file is part of Project Control Center (PCC).
# 
# Project Control Center (PCC) is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# Project Control Center (PCC) is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with Project Control Center (PCC).  If not, see <http://www.gnu.org/licenses/>.
# 
# Copyright 2010 Dmitri Anatol'evich Pisarenko
#

require 'date'

class DefaultProcessEndTimeTuple
	include Java::at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple
	
	attr_accessor :processId, :endDateTime
	
	def initialize(processId, endDateTime)
		logger = org.slf4j.LoggerFactory.getLogger("DefaultProcessEndTimeTuple")
		
		@processId = Integer(processId.tr('"', ' ').tr('T', ' '))
		@endDateTime = Java::at.silverstrike.pcc.impl.jruby.RubyDateTimeUtils.getDate(
			endDateTime.year, endDateTime.month-1, endDateTime.mday, endDateTime.hour, 
			endDateTime.min) 
		
	end
	
	def getProcessId
		@processId
	end
	
	def setProcessId(aProcessId)
		@processId = aProcessId
	end
	
	def getEndDateTime
		@endDateTime
	end
	
	def setEndDateTime(aDate)
		@endDateTime = aDate
	end
end

class DefaultTj3DeadlinesFileParser
	include Java::at.silverstrike.pcc.api.tj3deadlinesparser.Tj3DeadlinesFileParser
	
	def setInputFileName(inputFileName)
		@inputFileName = inputFileName
	end
	
	def getProcessEndTimes
		@processEndTimes
	end
	
	def run
		logger = org.slf4j.LoggerFactory.getLogger("DefaultTj3DeadlinesFileParser.rb")
		
		logger.debug("@inputFileName = #{@inputFileName}")
				
		@processEndTimes = java.util.LinkedList.new
		
		File.open(@inputFileName, "r") do |file|
			firstRow = true
			while line = file.gets
				if firstRow != true
					row = line.partition(";")
	
					logger.debug("datetime: #{row[2]}")
	
					tuple = DefaultProcessEndTimeTuple.new(row[0], DateTime.parse(row[2]))
					@processEndTimes.add(tuple)
				else
					firstRow = false
				end
			end
		end
	end
end

