package cmsc433.p2.tests;

import cmsc433.p2.Simulation;
import cmsc433.p2.Validate;
import org.junit.Test;

public class SharedTests {

    @Test
    public void TestSimpleRun() {


        int numCustomers = 1;
        int numCooks = 1;
        int numTables = 1;
        int machineCapacity = 1;
        boolean randOrders = false;

        Validate.validateSimulation(
                Simulation.runSimulation(
                        numCustomers, numCooks,
                        numTables, machineCapacity,
                        randOrders
                )
        );

    }
}
