package engine.physics;

import engine.util.math.Vec3;

public record Ray(Vec3 from, Vec3 dir, double maxDistance)
{

}