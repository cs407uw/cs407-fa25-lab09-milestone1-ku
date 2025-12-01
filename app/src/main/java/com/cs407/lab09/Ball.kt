package com.cs407.lab09

class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        // FIRST call: just initialize acceleration history
        if (isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // Ignore invalid/zero dt
        if (dT <= 0f) return

        // ---- X axis (Eq. 1 & 2 from handout) ----
        val newVx = velocityX + 0.5f * (accX + xAcc) * dT
        val dx = velocityX * dT + (1f / 6f) * dT * dT * (3f * accX + xAcc)

        // ---- Y axis ----
        val newVy = velocityY + 0.5f * (accY + yAcc) * dT
        val dy = velocityY * dT + (1f / 6f) * dT * dT * (3f * accY + yAcc)

        posX += dx
        posY += dy

        velocityX = newVx
        velocityY = newVy

        accX = xAcc
        accY = yAcc

        checkBoundaries()
    }

    fun checkBoundaries() {
        val radius = ballSize / 2f

        // LEFT wall
        if (posX - radius < 0f) {
            posX = radius
            velocityX = 0f
            accX = 0f
        }

        // RIGHT wall
        if (posX + radius > backgroundWidth) {
            posX = backgroundWidth - radius
            velocityX = 0f
            accX = 0f
        }

        // TOP wall
        if (posY - radius < 0f) {
            posY = radius
            velocityY = 0f
            accY = 0f
        }

        // BOTTOM wall
        if (posY + radius > backgroundHeight) {
            posY = backgroundHeight - radius
            velocityY = 0f
            accY = 0f
        }
    }

    fun reset() {
        val radius = ballSize / 2f

        posX = backgroundWidth / 2f
        posY = backgroundHeight / 2f

        velocityX = 0f
        velocityY = 0f

        accX = 0f
        accY = 0f

        isFirstUpdate = true
    }
}

