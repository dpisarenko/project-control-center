#
# This file is part of Project Control Center (PCC).
# 
# 
# PCC (Project Control Center) project is intellectual property of 
# Dmitri Anatol'evich Pisarenko.
# 
# Copyright 2010 Dmitri Anatol'evich Pisarenko
# All rights reserved

rm pcc.log
mvn -DUSER_HOME="/home/dp118m/dev/pcc" jetty:run 2>&1 | tee pcc.log
