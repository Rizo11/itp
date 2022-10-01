cmake_minimum_required(VERSION 3.23)
project(CookBook C)

set(CMAKE_C_STANDARD 17)

add_executable(CookBook main.c)

add_library(message-objs
        OBJECT
        ../Random/library.h
        ../Random/library.c
)

target_compile_options(message-objs
        PRIVATE
        -Wall -fpic
        )

add_library(message-a
        STATIC
        $<TARGET_OBJECTS:message-objs>
        )
target_link_libraries(CookBook message-a)