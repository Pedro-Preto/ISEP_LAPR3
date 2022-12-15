#ifndef ASM_H
#define ASM_H
/* Structure to hold information about the battery */
typedef struct{
	int battery_capacity; /* battery_capacity - 4 bytes */
	int current_energy; /* current_energy - 4 bytes */
	int charge_efficiency; /* charge_efficiency - 4 bytes */
	int scooterID; /* scooterID - 4bytes */
	int parkSlotID; /* parkSlotID - 4bytes */
	char *courierNif; /* Courier NIF - pointer 4 bytes */
} scooter_t; /* Total 20 bytes */

/* Structure to hold the information about the charging time of a scooter */
typedef struct{
	int hours; /* hours remaining - 4bytes */
	int minutes; /* minutes remaining - 4 bytes */
	int seconds; /* seconds remaining - 4 bytes */
} charging_t; /* Total 20 bytes */ 

void estimate_charging_time(scooter_t *scooter, int current_intensity, charging_t *charging_time);
#endif

