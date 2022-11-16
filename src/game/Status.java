package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    FOLLOWBEHAVIOUR, // use this status to see the actor has capability to follow
    DORMANT, // use this status to see if Koopa is Dormant
    INVINCIBLE, // use this status to see if a player has consumed PowerStar and is under its effect
    FADINGDURATION, // use this status to see the fading status of powerstar
    FIRE, //actor has active fire flower ability
    LAVA, // actor can enter the lavamap
    FLYINGKOOPA, // actors that can fly
    HASWRENCH,// actor has wrench
    NONMOVEABLE, // actor can't wander around
    BOWSER, // actor is a bowser
}
