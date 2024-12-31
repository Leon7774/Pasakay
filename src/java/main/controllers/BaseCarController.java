package main.controllers;

import main.objects.Agent;
import main.objects.Car;

public interface BaseCarController {

    Car getCar();
    Object getParentController();
}

