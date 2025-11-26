package carWashTask5.enums;

import lombok.Getter;

@Getter
public enum WashType {
    // Basic Services
    EXPRESS_WASH(160, 25),        // Quick exterior wash (usually automated or exterior only)
    STANDARD_WASH(300, 45),       // Exterior wash + basic interior cleaning (vacuum, dust wipe-down)

    // Detailed and Specialized Services
    FULL_SERVICE_WASH(600, 90),   // Complete wash (exterior + interior + trunk + mats)
    PREMIUM_DETAILING(3000, 480),   // Full suite of services with high-end chemicals, polishing, dry cleaning
    INTERIOR_DETAILING(2000, 360),  // Deep interior dry cleaning only
    EXTERIOR_POLISHING(1100, 300),  // Exterior body polishing services only

    // Additional Services
    ENGINE_WASH(500, 40),         // Under-the-hood cleaning
    UNDERCARRIAGE_WASH(350, 30),  // Cleaning of the vehicle's underside
    WAX_APPLICATION(850, 60),     // Application of protective wax/coating
    WHEEL_CLEANING(300, 20);       // Specialized cleaning and protection of rims/wheels

    private final int basePrice;
    private final int estimatedDurationMinutes;

    WashType(int basePrice, int estimatedDurationMinutes) {
        this.basePrice = basePrice;
        this.estimatedDurationMinutes = estimatedDurationMinutes;
    }
}