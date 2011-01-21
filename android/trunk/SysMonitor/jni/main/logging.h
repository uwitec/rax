
#ifndef _LOGGING_H_
#define _LOGGING_H_

#ifndef LOG_TAG
#define	LOG_TAG		"RaxLog"
#endif

#define	LOG_ENABLE	1

#if LOG_ENABLE

#include <android/log.h>

#define LOGV(...)	__android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
#define LOGD(...)	__android_log_print(ANDROID_LOG_DEBUG,   LOG_TAG, __VA_ARGS__)
#define LOGI(...)	__android_log_print(ANDROID_LOG_INFO,    LOG_TAG, __VA_ARGS__)
#define LOGW(...)	__android_log_print(ANDROID_LOG_WARN,    LOG_TAG, __VA_ARGS__)
#define LOGE(...)	__android_log_print(ANDROID_LOG_ERROR,   LOG_TAG, __VA_ARGS__)

#else /* #if LOG_ENABLE	*/

#define LOGV(...)	{}
#define LOGD(...)	{}
#define LOGI(...)	{}
#define LOGW(...)	{}
#define LOGE(...)	{}

#endif	/* #ifndef LOG_ENABLE	*/

#endif /* #ifndef _LOGGING_H_ */

// vim:ts=8:sw=4:
