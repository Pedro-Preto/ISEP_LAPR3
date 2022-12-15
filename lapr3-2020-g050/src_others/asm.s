.section .data
	.equ FACTOR, 100
	.equ DATA_SIZE, 20
	.equ BATTERY_CAPACITY_OFFSET, 0
	.equ CURRENT_ENERGY_OFFSET, 4
	.equ CHARGE_EFFICIENCY_OFFSET, 8
	.equ HOURS_OFFSET, 0
	.equ MINUTES_OFFSET, 4
	.equ SECONDS_OFFSET, 8

.section .text
	.global estimate_charging_time

estimate_charging_time:
	
	#prologue
	pushl %ebp #save previous stack from pointer
	movl %esp, %ebp #the stack frame pointer for the function

	#callee saves registers ebx, edi, esi
	pushl %ebx
	pushl %edi
	pushl %esi

	#function parameters
	movl 8(%ebp), %edx #first parameter (scooter)  
        movl 12(%ebp), %esi #second parameter (current_intensity)	

	cmpl $0, %esi
	je end

	#scooter_t 
	movl BATTERY_CAPACITY_OFFSET(%edx), %ecx #first parameter (battery_cappacity)
	movl CURRENT_ENERGY_OFFSET(%edx), %edi #second parameter (current_battery)
	movl CHARGE_EFFICIENCY_OFFSET(%edx), %eax #third parameter (efficiency)

	movl $FACTOR, %ebx #moves 100 to ebx
	cdq
	mull %ebx #multiplies efficiency by 100
	
	movl %eax, %edx #moves efficiency multiplied by 100 to ecx
	
	pushl %edx 
	
	movl %edi, %eax #moves current_battery to %eax	

	cdq
	mull %ebx #multiplies current_battery by 100
	movl %eax, %edi #moves current_battery to %edi

	movl %ecx, %eax #moves battery_capacity to eax
	cdq 
	mull %ebx #multiplies battery_capacity by 100	
	subl %edi, %eax #does battery_capacity - current_battery	

	cdq
	mull %ebx #does (battery_capacity - current_battery) x 100
	cdq
	mull %ebx #does ((battery_capacity - current_battery) x 100 x 100)
	
	popl %edx #retrieves efficiency
	pushl %eax #restores ((battery_capacity - current_battery) x 100 x 100)
	
	movl %edx, %eax #moves efficiency to eax
	cdq
	mull %esi #multiplies current_intensity with efficiency
#	cdq
#	mull %ebx #multiplies current_intensity with efficiency by 100
	
	movl %eax, %ecx #moves current_intensity with efficiency by 100 to ecx
	popl %eax #retrieves ((battery_capacity - current_battery) x 100 x 100)
	
	cdq
	divl %ecx # does ((battery_capacity - current_battery) x 100 x 100) / efficiency x current_intensity x 100 x 100

	#function parameters
	movl 16(%ebp), %esi #3rd parameter (charging_time)
	
	movl $60, %edi #moves 60 to edi, in order to divide by
	
	cdq
	divl %ebx #does time / 100:   eax = hours, edx = minutes and seconds --- number of hours moved to struct

	movl %eax, HOURS_OFFSET(%esi) #moves hours to charging_time struct

	movl %edx, %eax #moves minutes and seconds to eax	
	
	cdq
	mull %edi #multiplies minutes and seconds per 60

	cdq
	divl %ebx #divides minutes and seconds per 100:    eax = minutes, edx = seconds --- number of minutes moved to struct

	movl %eax, MINUTES_OFFSET(%esi) #moves minutes to charging_time struct 

	movl %edx, %eax #moves seconds to eax	

	cdq
	mull %edi #multiplies seconds per 60

	cdq 
	divl %ebx #divides seconds by 100: eax = seconds -> move seconds to struct
	
	movl %eax, SECONDS_OFFSET(%esi) #moves seconds to charging_time struct

end:
	#callee restores registers ebx, edi, esi
	popl %esi
	popl %edi
	popl %ebx
	
	movl %ebp, %esp #restores the previous stack pointer
	popl %ebp #restores the previous stack frame pointer
	ret
