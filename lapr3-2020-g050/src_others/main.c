#include <stdio.h>
#include <stdlib.h>
#include "asm.h"
#include <unistd.h>
#include <string.h>
#include <glob.h>
#include <time.h>

#define OUTPUT_FLAG_FILENAME_SIZE 40
#define OUTPUT_FILENAME_SIZE 35
#define FLAG_FILENAME_SIZE 34
#define FILENAME_SIZE 29
#define NIF_SIZE 10

#define ESTIMATE "estimate"
#define FLAG_FILENAME "lock*.data.flag"
#define FLAG ".flag"

#define INTENSITY_PARAMETER "-intensity"
#define SLOTS_PARAMETER "-slots"
#define FILE_EXIT "program.exit"

int scooters_number = 0, park_current_intensity = 0, park_slots = 0;

scooter_t newScooter(int battery_capacity, int current_energy, int charge_efficiency, int scooterID, int parkSlotID, char * courierNif)
{

	scooter_t scooter;
	scooter_t *scooter_p = &scooter;

	scooter_p -> battery_capacity = battery_capacity;                            
	scooter_p -> current_energy = current_energy;                          
	scooter_p -> charge_efficiency = charge_efficiency;                              
	scooter_p -> scooterID = scooterID;                       
	scooter_p -> parkSlotID = parkSlotID; 
	scooter_p -> courierNif = courierNif;

	return scooter;
}

void printScooter(scooter_t *scooter_p, FILE *fpointer)
{
	fprintf(fpointer,"#Scooter:\n");
	fprintf(fpointer,"# - Battery Capacity : %d unidades\n", scooter_p -> battery_capacity);
	fprintf(fpointer,"# - Current Energy : %d unidades\n", scooter_p -> current_energy);
	fprintf(fpointer,"# - Charge Efficiency : %d / 100\n", scooter_p -> charge_efficiency);
	fprintf(fpointer,"# - Scooter ID : %d\n", scooter_p -> scooterID);
	fprintf(fpointer,"# - Slot park ID: %d\n", scooter_p -> parkSlotID);
	fprintf(fpointer,"# - Courier NIF : %s\n", scooter_p -> courierNif);
	fprintf(fpointer,"#\n");
	
	fprintf(fpointer,"%d,%d,%s\n",scooter_p -> scooterID,scooter_p -> parkSlotID,scooter_p -> courierNif);
}

void printFScooter(scooter_t *scooter_p)
{
	
	printf("Scooter:%p\n",scooter_p);
	printf(" - Battery Capacity : %d unidades\n", scooter_p -> battery_capacity);
	printf(" - Current Energy : %d unidades\n", scooter_p -> current_energy);
	printf(" - Charge Efficiency : %d / 100\n", scooter_p -> charge_efficiency);
	printf(" - Scooter ID : %d\n", scooter_p -> scooterID);
	printf(" - Park Slot ID : %d\n", scooter_p -> parkSlotID);
	printf(" - Courier NIF : %s\n", scooter_p -> courierNif);

	printf("\n\n");
}

void printChargingTime(charging_t *charging_time, int current_intensity, FILE *fpointer)
{
	fprintf(fpointer,"#Current Intensity: %d\n#Charging time -> %d h %d min %d seg\n",current_intensity, charging_time -> hours, charging_time -> minutes, charging_time -> seconds);

	fprintf(fpointer,"%d,%d,%d\n",charging_time -> hours, charging_time -> minutes, charging_time -> seconds);
}

void printFChargingTime(charging_t *charging_time)
{
	printf("Charging time -> %d h %d min %d seg\n\n",charging_time -> hours, charging_time -> minutes, charging_time -> seconds);
}

void createEstimateFileName(char* printFileName)
{
	time_t now;
		
	time(&now);
	
	int hours, minutes, seconds, day, month, year;

	struct tm *local = localtime(&now);

	hours = local->tm_hour;          // get hours since midnight (0-23)
	minutes = local->tm_min;         // get minutes passed after the hour (0-59)
	seconds = local->tm_sec;         // get seconds passed after minute (0-59)

	day = local->tm_mday;            // get day of month (1 to 31)
	month = local->tm_mon + 1;       // get month of year (0 to 11)
	year = local->tm_year + 1900;    // get year since 1900
	
	sprintf(printFileName, "estimate_%02d_%02d_%02d_%02d_%02d_%02d.data", year, month, day, hours, minutes, seconds);
		
//		printf("\nPrint file name: %s\n\n\n\n", printFileName);
}

void showParkStatus(scooter_t *scooters)
{
	printf("\n------------------------------\n\n");	
	printf("Vehicles charging: %d | Free slots: %d \n", scooters_number, park_slots - scooters_number);
	printf("Park total current intensity: %d A \n", park_current_intensity); 
	if(scooters_number == 0)
		printf("PARK IS EMPTY!\n");
	else
	{
		printf("Individual Park Slot current intensity: %d A\n\n", park_current_intensity / scooters_number); 
	
		int idx;
		for(idx = 0; idx < scooters_number; idx++)
		{
			printf("\tParkSlot ID: %d | Scooter ID: %d\n",(scooters+idx)->parkSlotID, (scooters+idx)->scooterID);
		}
	}
	printf("\n------------------------------\n\n");
}

void estimateEnergy(scooter_t *scooters, int current_intensity)
{
	FILE *fpointer;
		
	/* Initialize print file name */
	char* printFileName = NULL;
	char* printFlagFileName = NULL;
	/* Initialize charging time */
	charging_t *charging_time = NULL;
	
	/* Alocate memory to printFileName */
	printFileName = (char*) malloc(sizeof(char) * OUTPUT_FILENAME_SIZE);
	printFlagFileName = (char *) malloc(sizeof(char) * OUTPUT_FLAG_FILENAME_SIZE);
	/* Alocates memory for charging_time */
	charging_time = (charging_t *) malloc(sizeof(charging_t));
	
	int idx;
	for(idx = 0; idx < scooters_number; idx++)
	{
		
		estimate_charging_time((scooters+idx), current_intensity, charging_time);
						
		createEstimateFileName(printFileName);
	
		fpointer = fopen(printFileName, "w+");

		printScooter((scooters+idx),fpointer);	
		printChargingTime(charging_time, current_intensity, fpointer);	
		
		fclose(fpointer);
		
		memcpy(printFlagFileName, printFileName, OUTPUT_FILENAME_SIZE);
		strcat(printFlagFileName, FLAG);
		
		printf(printFlagFileName);
		fpointer = fopen(printFlagFileName, "w+");	
		fclose(fpointer);
		
		
		sleep(1); //waits 1 second for next file
	}

	/* Free file name memory */
	free(printFileName);
	free(printFlagFileName);
	free(charging_time);
}

char checkDirectory(glob_t *files, scooter_t *scooters)
{
	FILE *fpointer;
	int returnValue;
	int idx;
	char newScooters = 0;
	
	files -> gl_pathc = 0;
	files -> gl_pathv = NULL;
	files -> gl_offs = 0;

	returnValue = glob(FLAG_FILENAME, GLOB_NOCHECK | GLOB_NOSORT, NULL, files);

	if(returnValue == 0)
	{

		for(idx = 0; idx < files -> gl_pathc; idx++)
		{
	//		printf( "DETECTED: [%d]: %s\n", idx, files->gl_pathv[idx] );

			/* Alocate memory for fname */                         
			char *fname = NULL;  //ficheiro a ler                             
			char *flagName = NULL;
			fname = (char *) malloc(FILENAME_SIZE * sizeof(char));
			flagName = (char *) malloc(FLAG_FILENAME_SIZE * sizeof(char));

			strncpy(fname, files -> gl_pathv[idx], FILENAME_SIZE);
			strncpy(flagName, files -> gl_pathv[idx], FLAG_FILENAME_SIZE);

			*(fname+FILENAME_SIZE) = '\0'; //Adds the \0 = NULL to the end of the string.
	
			*(flagName+FLAG_FILENAME_SIZE) = '\0'; //Adds the \0 = NULL to the end of the string.
			
			//printf("\n------------------------------\n\n");
			
			if(access( fname, F_OK) == 0 )
			{
		
				printf("\nFile detected!\n \'%s\'\n",fname);

				fpointer = fopen(fname, "rw");	//attributes pointer to open file
				
				int battery_capacity, current_energy, charge_efficiency, scooterID, parkSlotID;
				/* Initialize courier NIF */
				char *courierNIF = NULL;
				/* Alocates memory for courier NIF */
				courierNIF = (char *) malloc(NIF_SIZE*sizeof(char));
				
				fscanf(fpointer, "%d\n", &battery_capacity);
				fscanf(fpointer, "%d\n", &current_energy);
				fscanf(fpointer, "%d\n", &charge_efficiency);
				fscanf(fpointer, "%d\n", &scooterID);
				fscanf(fpointer, "%d\n", &parkSlotID);
				fscanf(fpointer, "%s\n", courierNIF);

				fclose(fpointer);

				if(battery_capacity > 0 && current_energy >= 0 && current_energy <= battery_capacity && charge_efficiency > 0 && charge_efficiency <= 100 && scooterID > 0 && parkSlotID > 0 && courierNIF != NULL)
				{
					int i, valid = 1;
					for(i=0; i<scooters_number; i++)
					{
							if(parkSlotID == (scooters+i) -> parkSlotID){
									valid = 0;
									break;
							}                    
					}
					
					if(valid == 1)
					{
						if(scooters_number != park_slots)
						{
							*(scooters+scooters_number) = newScooter(battery_capacity,current_energy,charge_efficiency,scooterID,parkSlotID,courierNIF);

							scooters_number = scooters_number + 1;
							/* Makes return value 1 */
							newScooters = 1;
						}
						else
						{
							break;
						}
          }
					else
					{
						printf("\n\t- Scooter already exists!!\n");
					}
					printf("\nDeleting file:\n");
					printf("-\'%s\'...",fname);

					if (remove(fname) == 0) 
						printf("\nFile deleted successfully!\n\n");
					else
						printf("\nERROR: unable to delete file!\n");
				}
				else
				{
					printf("\nFILE: \'%s\' NOT LOCKED.\n",fname);
					printf("\nDeleting file:\n");
				}
					
				printf("-\'%s\'...",flagName);
				if (remove(flagName) == 0) 
					printf("\nFile deleted successfully!\n\n");
				else
					printf("\nERROR: unable to delete file!\n");
				
				//printf("------------------------------\n\n");
				
			} 
			
			
			/* Free alocated memory */
			free(fname);
			free(flagName);
		
			
		}
		
	}
	return newScooters;	
}

char checkExitFile(void)
{
	char returnValue = 0;
	/* IF THE FILE_EXIT IS DETECTED CLOSE THE PROGRAM */
	if(access(FILE_EXIT, F_OK)==0)
	{
		printf("\n\'%s\' DETECTED ON THE DIRECTORY\nDo you want to exit: (y|n) ",FILE_EXIT);
		char option;
		scanf("%c",&option);
		getchar(); //cleans buffer
		if(option=='y')
		{
			printf("EXITING THE PROGRAM....\n");
			returnValue = 1;
		}
		else
			printf("RESUMING PROGRAM EXECUTION... \n");
			
		printf("DELETING \'%s\'...\n",FILE_EXIT);
		remove(FILE_EXIT);
	}
	
	return returnValue;
}

/* 
	1st parameter = file name
	-intensity 
	-slots 
*/
int main(int argc, char* argv[])
{

	if(argc != 5)
	{
		printf("ERROR: INVALID NUMBER OF PARAMETERS!\nPLEASE USE \'%s\' and \'%s\'\n",INTENSITY_PARAMETER,SLOTS_PARAMETER);
		exit(1);
	}
		
	int idx;
	for(idx = 1; idx < argc; idx+=2)
	{
		/* atoi() converts a string to an integer */
		if(strcmp(INTENSITY_PARAMETER,*(argv+idx)) == 0)
			park_current_intensity = atoi(*(argv+idx+1));
		else if(strcmp(SLOTS_PARAMETER,*(argv+idx)) == 0)
			park_slots = atoi(*(argv+idx+1));
		else
		{
			printf("ERROR: OPTION \'%s\' IS NOT VALID!\nPLEASE USE \'%s\' and \'%s\'\n",*(argv+idx),INTENSITY_PARAMETER,SLOTS_PARAMETER);
			exit(1);
		}
			
	}
	
	if(park_slots <= 0 || park_current_intensity < park_slots || park_slots <= 0)
	{
		printf("ERROR: INVALID PARAMETER!! \n %s -> %d \n %s -> %d\nPLEASE USE A POSITIVE VALUE AND THE PARK ENERGY SHOULD BE BIGGER THAN THE NUMBER OF SLOTS!\n"
		,INTENSITY_PARAMETER,park_current_intensity,SLOTS_PARAMETER, park_slots);
		exit(1);
	}
	
	glob_t paths;
	glob_t *files = &paths;
	
	/* Initialize array */
	scooter_t *scooters = NULL;
	/* Alocate memory for scooters  */         
	//scooters = (scooter_t *) malloc(scooters_number*sizeof(scooter_t));
	scooters = (scooter_t *) calloc(park_slots,sizeof(scooter_t));
	
	printf("To exit the program create \'%s\' on the directory\n",FILE_EXIT);
	sleep(2);
	
	char quit = 0;
	
	while(!quit)
	{
		showParkStatus(scooters);
		if(scooters_number < park_slots)
		{
			if(checkDirectory(files, scooters) == 1)
			{
				int current_intensity = park_current_intensity / scooters_number;
				estimateEnergy(scooters, current_intensity); 
			}
			else
			{
				printf("WAITING 20SEC  ... \n");	
				sleep(20); //waits for 20sec
			}
		}
		else
		{
			printf("PARK IS FULL!\n");
			quit = checkExitFile();
			printf("WAITING 60SEC  ... \n");	
			sleep(60); //waits for 60sec
		}	
		quit = checkExitFile();
	}
	
	/* Free alocated memory */
	free(scooters);
	globfree(files);
	
	return 0;
}
