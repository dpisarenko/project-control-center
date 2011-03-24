#
# This file is part of Project Control Center (PCC).
# 
# 
# PCC (Project Control Center) project is intellectual property of 
# Dmitri Anatol'evich Pisarenko.
# 
# Copyright 2010, 2011 Dmitri Anatol'evich Pisarenko
# All rights reserved

mvn cobertura:instrument
rm pcc.log
mvn jetty:run 2>&1 | tee pcc.log
