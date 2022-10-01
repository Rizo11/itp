cmake_minimum_required(VERSION 3.23)
project(hello LANGUAGES C)

# bool var USE_STATIC
option(USE_STATIC "Use static library" ON)

# log a message
message(STATUS "OS: ${CMAKE_SYSTEM_NAME}")
message(STATUS "COMPILE WITH STATIC LIBRARY? ${USE_STATIC}")

add_executable(hello main.c)

add_library(message-objs
    OBJECT
    ./lib/stringL.h
    ./lib/stringL.c
)

target_compile_options(message-objs
    PRIVATE
    -Wall
)

if(USE_STATIC)
    add_library(message-a
    STATIC
        $<TARGET_OBJECTS:message-objs>
    )
    target_link_libraries(hello message-a)

else()
    add_library(message-so
    SHARED
        $<TARGET_OBJECTS:message-objs>
    )
    target_link_libraries(hello message-so)
endif()