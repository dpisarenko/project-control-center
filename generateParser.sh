#
# This file is part of Project Control Center (PCC).
# 
# 
# PCC (Project Control Center) project is intellectual property of 
# Dmitri Anatol'evich Pisarenko.
# 
# Copyright 2010 Dmitri Anatol'evich Pisarenko
# All rights reserved

java -cp /home/dp118m/dev/pcc-lp/src/main/webapp/WEB-INF/lib/antlr-3.2.jar org.antlr.Tool src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/Bookings.g 

mv Bookings.tokens src/main/java/at/silverstrike/pcc/impl/tj3bookingsparser/grammar/
