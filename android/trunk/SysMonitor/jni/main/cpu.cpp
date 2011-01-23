/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <stdio.h>
#include <string.h>
#include "cpu.h"
#include "logging.h"

double
get_cpu_usage()
{
    static long	last_cpu_used	= 0;
    static long	last_cpu_idle	= 0;
    FILE *	fp		= NULL;
    double	usage		= 0;
    long	cpu_used	= 0;
    long	cpu_user	= 0;
    long	cpu_nice	= 0;
    long	cpu_system	= 0;
    long	cpu_idle	= 0;
    long	cpu_iowait	= 0;
    long	cpu_irq		= 0;
    long	cpu_softirq	= 0;
    long	cpu_reserve	= 0;
    //LOGV("get_cpu_usage+");

    if (NULL != (fp = fopen("/proc/stat", "r"))) {
	if (EOF != fscanf(fp, "cpu %ld %ld %ld %ld %ld %ld %ld %ld", 
		&cpu_user, &cpu_nice, &cpu_system, &cpu_idle,
		&cpu_iowait, &cpu_irq, &cpu_softirq, &cpu_reserve)) {

	    /*
	    LOGV("get_cpu_usage user:%ld nice:%ld system:%ld idle:%ld iowait:%ld irq:%ld softirq:%ld reserve:%ld",
		    cpu_user, 
		    cpu_nice, 
		    cpu_system, 
		    cpu_idle, 
		    cpu_iowait, 
		    cpu_irq, 
		    cpu_softirq, 
		    cpu_reserve);
	    */
	    /*
	    LOGD("get_cpu_usage total_idle:%ld total_used:%ld", 
		    cpu_idle - last_cpu_idle, 
		    cpu_used - last_cpu_used);
	    */

	    cpu_used	= cpu_user + cpu_nice + cpu_system + cpu_idle + cpu_iowait + cpu_irq + cpu_softirq;
	    if (cpu_used != last_cpu_used) {
		usage		= 1 - (double)(cpu_idle - last_cpu_idle) / (double)(cpu_used - last_cpu_used);
		last_cpu_used	= cpu_used;
		last_cpu_idle	= cpu_idle;
	    }
	}
	fclose(fp);
    }
    //LOGV("get_cpu_usage- usage:%f%%", usage * 100);
    return usage; 
}

// vim:ts=8:sw=4:
