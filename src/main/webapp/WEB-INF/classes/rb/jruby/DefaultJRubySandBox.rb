# This file is part of Project Control Center (PCC).
# 
# PCC (Project Control Center) project is intellectual property of 
# Dmitri Anatol'evich Pisarenko.
# 
# Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
# All rights reserved


class DefaultJRubySandBox
	include Java::at.silverstrike.pcc.api.jruby.JRubySandBox
	
	def setStringInput(txt)
		@stringInput = txt
	end
	
	def setIntInput(number)
		@intInput = number
	end
	
	def getTextOutput
		@textOutput
	end
	
	def getIntOutput
		@intOutput
	end
	
	def run
		puts "This text comes from Ruby!"
	end
end