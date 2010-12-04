# This file is part of Project Control Center (PCC).
# 
# PCC (Project Control Center) project is intellectual property of 
# Dmitri Anatol'evich Pisarenko.
# 
# Copyright 2010 Dmitri Anatol'evich Pisarenko
# All rights reserved

resource R${id} "${abbreviation}"
{
	limits { dailymax ${dailyLimitInHours}h }
}
