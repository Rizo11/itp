FILES = random.c
CFLAGS = -g -Wall

all: clean
	gcc $(FILES) -o main
	./main

pre:
	gcc $(CFLAGS) -E $(FILES)
clean:
	rm -rf main