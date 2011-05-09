# This file is part of Project Control Center (PCC).
# 
# PCC (Project Control Center) project is intellectual property of 
# Dmitri Anatol'evich Pisarenko.
# 
# Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
# All rights reserved

require 'date'

class DefaultProcessEndTimeTuple
	include Java::at.silverstrike.pcc.api.tj3deadlinesparser.ProcessEndTimeTuple
	
	attr_accessor :processId, :endDateTime
	
	def initialize(processId, endDateTime)
		logger = org.slf4j.LoggerFactory.getLogger("DefaultProcessEndTimeTuple")
		
		@processId = Integer(processId.split(".").last.tr('"', ' ').tr('T', ' '))
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

