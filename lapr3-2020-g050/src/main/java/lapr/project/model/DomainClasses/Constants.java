package lapr.project.model.DomainClasses;

public class Constants {

    /**
     * Hide the implicit constructor.
     */
    private Constants(){

    }

    /**
     * Predefined ROLES
     */
    public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
    public static final String ROLE_PHARMACY_ADMINISTRATOR = "PHARMACY ADMINISTRATOR";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_COURIER = "COURIER";

    /**
     * Default Values
     */
    public static final String DEFAULT_STRING_VALUES = "DEFAULT_VALUE";
    public static final Integer DEFAULT_INT_VALUES = 0;
    public static final Double DEFAULT_DOUBLE_VALUES = 0.0;

    /**
     * Courier Default Values
     */
    public static final Double MEDIUM_WEIGHT = 75.0;
    public static final Double MAX_PAYLOAD = 10.0;

    /**
     * Scooter Default Values - following this model: https://www.carandbike.com/ather-bikes/450
     */
    public static final Double SCOOTER_BATTERY_SIZE = 2400.0; //Wh
    public static final Double SCOOTER_BATTERY_CONSUMPTION = 2400.0 / 75.0; //Full charge +/- 75KM
    public static final Double SCOOTER_BATTERY_EFFICIENCY = 0.60; //Lithium-ion efficiency ranges from 20-60%
    public static final Double SCOOTER_WEIGHT = 118.0; //kg
    public static final Double SCOOTER_WIDTH = 0.700; //m
    public static final Double SCOOTER_HEIGHT = 1.250; //m
    public static final Double SCOOTER_MOTOR_POWER = 5398.8671; //W -> ORIGINAL = 7.24bhp
    public static final Double SCOOTER_MAX_SPEED = 22.2222; //80kmph = 22.2222 mps

    /**
     * Drone Default Values
     */
    public static final Double DRONE_FRONTAL_AREA = 0.03;
    public static final Double DRONE_WIDTH = 0.03;
    public static final Double HORIZONTAL_SPEED = 16.0; //m s^-1
    public static final Double ASCENT_DESCENT_SPEED = 6.0; //m s^-1
    public static final Double FLIGHT_ALTITUDE = 100.0; //m TODO CHANGE BACK TO 150
    public static final Double DRONE_PLATFORM_ALTITUDE = 10.0; //m above ground
    public static final Double DRONE_BATTERY_SIZE = 15000.0; //mAh
    public static final Double DRONE_BATTERY_EFFICIENCY = 0.80; //LiPo efficiency from 80%
    public static final Double DRONE_WEIGHT = 3.3;//kg
    public static final Double DRONE_MOTOR_POWER = 3000.0; //W
    public static final Double D_AIR_DENSITY = 1.225;
    public static final Double D_AIR_DRAG_COEFFICIENT = 1.5;

    /**
     * Path Calculation Constants
     */
    public static final Double AIR_DENSITY = 1.324;
    public static final Double AIR_DRAG_COEFFICIENT = 1.5;


    /**
     * Demonstration Variables
     */
    public static final Double S_AIR_DRAG_COEFFICIENT = 1.1;
    public static final Double D_VELOCITY = 30.0; //KMPH
}
