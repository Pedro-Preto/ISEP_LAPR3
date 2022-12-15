

compile: main.o asm.o
	gcc -Wall -g main.o asm.o -o park

main.o: main.c asm.h
	gcc -Wall -c -g main.c

asm.o: asm.s
	gcc -Wall -c -g asm.s

run: compile
	./park -intensity 30 -slots 20

clean: 
	rm -f park asm.o main.o estimate*


