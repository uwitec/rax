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
#include "memory.h"
#include "logging.h"

double
get_memory_usage(long * report_free)
{
    FILE *	fp		= NULL;
    double	usage		= 0;
    long	value		= 0;
    long	mem_total	= 0;
    long	mem_free	= 0;
    char	token[128];
    LOGV("get_memory_usage- report_free:%p", report_free);

    if (NULL != (fp = fopen("/proc/meminfo", "r"))) {
	while (EOF != fscanf(fp, "%s %ld kB", token, &value)) {
	    LOGV("get_memory_usage toker:%s value:%ld", token, value);
	    if (0 == strncmp(token, "MemTotal", 8)) {
		mem_total = value;
	    }
	    if (0 == strncmp(token, "MemFree", 7)) {
	        mem_free = value;
	    }
	    if (mem_free && mem_total) break;
	}
	LOGD("get_memory_usage mem_total:%ld mem_free:%ld", mem_total, mem_free);
	if (mem_total) {
	    usage = (mem_total - mem_free) / (double)mem_total;
	    if (NULL != report_free) {
		*report_free = mem_free;
	    }
	}
	fclose(fp);
    }
    LOGV("get_memory_usage- usage:%f%%", usage * 100);
    return usage;
}

// vim:ts=8:sw=4:
