cmake_minimum_required(VERSION 3.23)
project(cmake_testapp)

set(CMAKE_CXX_STANDARD 14)

add_executable(cmake_testapp main.cpp)
add_executable(cmake_testapp_calc calc.cpp)
#          (library_name STATIC .cpp_file)
add_library(test_library STATIC calc.cpp)