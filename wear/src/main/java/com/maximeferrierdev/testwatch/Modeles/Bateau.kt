package com.maximeferrierdev.testwatch.Modeles

import java.io.Serializable

data class Bateau (var nom : String ?= null, var type : String ?= null, var nombreJoursrestants : Int ?= 0, var icone : Int ?= null) : Serializable