package com.hibernetdemo.main;


import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.hibernetdemo.dao.*;
import com.hibernetdemo.entity.*;

public class App {

    // Helper method to safely read dates
    private static LocalDate readDate(Scanner sc) {
        String input = sc.next();
        if (input.contains("/")) {
            return LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
        return LocalDate.parse(input); // yyyy-MM-dd
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MineSiteDAO mineDAO = new MineSiteDAO();
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        WorkerDAO workerDAO = new WorkerDAO();
        ShiftLogDAO shiftDAO = new ShiftLogDAO();
        ProductionLogDAO productionDAO = new ProductionLogDAO();
        EquipmentUsageDAO usageDAO = new EquipmentUsageDAO();
        SafetyIncidentDAO incidentDAO = new SafetyIncidentDAO();

        while (true) {

            System.out.println("\n--- MineOps Lite ---");
            System.out.println("1. Register Mine");
            System.out.println("2. Update Mine Status");
            System.out.println("3. Register Equipment");
            System.out.println("4. Transfer Equipment Between Mines");
            System.out.println("5. Register Worker");
            System.out.println("6. Create Shift Log");
            System.out.println("7. Record Daily Production");
            System.out.println("8. Record Equipment Usage");
            System.out.println("9. Report Safety Incident");
            System.out.println("10. Close Safety Incident");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                // UC-CRUD-01
                case 1: {
                    MineSite m = new MineSite();

                    System.out.print("Mine ID: ");
                    m.setMineId(sc.nextInt());

                    System.out.print("Name: ");
                    m.setName(sc.next());

                    System.out.print("Location: ");
                    m.setLocation(sc.next());

                    System.out.print("Status (ACTIVE / INACTIVE): ");
                    m.setStatus(sc.next().toUpperCase());

                    mineDAO.save(m);
                    System.out.println("Mine registered successfully!");
                    break;
                }

                // UC-CRUD-02
                case 2: {
                    System.out.print("Mine ID: ");
                    int id = sc.nextInt();

                    MineSite mine = mineDAO.findById(id);
                    if (mine == null) {
                        System.out.println("Mine not found");
                        break;
                    }

                    System.out.print("New Status (ACTIVE / INACTIVE): ");
                    mineDAO.updateStatus(id, sc.next().toUpperCase());

                    System.out.println("Mine status updated");
                    break;
                }

                // UC-CRUD-03
                case 3: {
                    System.out.print("Equipment ID: ");
                    int equipmentId = sc.nextInt();

                    System.out.print("Mine ID: ");
                    int mineId = sc.nextInt();

                    if (mineDAO.findById(mineId) == null) {
                        System.out.println("Invalid Mine ID. Create mine first.");
                        break;
                    }

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Type: ");
                    String type = sc.next();

                    System.out.print("Status (WORKING / MAINTENANCE / BROKEN): ");
                    String status = sc.next().toUpperCase();

                    System.out.print("Purchase Date (YYYY-MM-DD or YYYY/MM/DD): ");
                    LocalDate date = readDate(sc);

                    Equipment eq = new Equipment();
                    eq.setEquipmentId(equipmentId);
                    eq.setMineId(mineId);
                    eq.setName(name);
                    eq.setType(type);
                    eq.setStatus(status);
                    eq.setPurchaseDate(date);

                    equipmentDAO.save(eq);
                    System.out.println("Equipment registered successfully!");
                    break;
                }

                // UC-CRUD-04
//                case 4: {
//                    System.out.print("Equipment ID: ");
//                    int eqId = sc.nextInt();
//
//                    Equipment eq = equipmentDAO.findById(eqId);
//                    if (eq == null) {
//                        System.out.println("Equipment not found");
//                        break;
//                    }
//
//                    if ("BROKEN".equalsIgnoreCase(eq.getStatus())) {
//                        System.out.println("Equipment is BROKEN. Cannot transfer.");
//                        break;
//                    }
//
//                    System.out.print("New Mine ID: ");
//                    int newMineId = sc.nextInt();
//
//                    if (mineDAO.findById(newMineId) == null) {
//                        System.out.println("Destination mine not found");
//                        break;
//                    }
//
//
//                    equipmentDAO.updateMine(eq, newMineId);
//
//                    System.out.println("Equipment transferred successfully!");
//                    break;
//                }

                // UC-CRUD-05
                case 5: {
                    System.out.print("Worker ID: ");
                    int workerId = sc.nextInt();

                    System.out.print("Mine ID: ");
                    int mineId = sc.nextInt();

                    if (mineDAO.findById(mineId) == null) {
                        System.out.println("Invalid Mine ID");
                        break;
                    }

                    System.out.print("Name: ");
                    String name = sc.next();

                    System.out.print("Role (SUPERVISOR / OPERATOR / SAFETY): ");
                    String role = sc.next().toUpperCase();

                    if (!role.equals("SUPERVISOR") &&
                        !role.equals("OPERATOR") &&
                        !role.equals("SAFETY")) {
                        System.out.println("Invalid role");
                        break;
                    }

                    System.out.print("Phone: ");
                    String phone = sc.next();

                    Worker w = new Worker();
                    w.setWorkerId(workerId);
                    w.setMineId(mineId);
                    w.setName(name);
                    w.setRole(role);
                    w.setPhone(phone);

                    workerDAO.save(w);
                    System.out.println("Worker registered successfully!");
                    break;
                }

                // UC-CRUD-06
                case 6: {
                    System.out.print("Shift ID: ");
                    int shiftId = sc.nextInt();

                    if (shiftDAO.findById(shiftId) != null) {
                        System.out.println("Duplicate Shift ID");
                        break;
                    }

                    System.out.print("Mine ID: ");
                    int mineId = sc.nextInt();

                    System.out.print("Supervisor ID: ");
                    int supId = sc.nextInt();

                    if (mineDAO.findById(mineId) == null || workerDAO.findById(supId) == null) {
                        System.out.println("Invalid Mine or Supervisor");
                        break;
                    }

                    System.out.print("Shift Date: ");
                    LocalDate date = readDate(sc);

                    System.out.print("Shift Type (DAY / NIGHT): ");
                    String type = sc.next().toUpperCase();

                    ShiftLog shift = new ShiftLog();
                    shift.setShiftId(shiftId);
                    shift.setMineId(mineId);
                    shift.setSupervisorId(supId);
                    shift.setShiftDate(date);
                    shift.setShiftType(type);

                    shiftDAO.save(shift);
                    System.out.println("Shift created successfully!");
                    break;
                }

                // UC-CRUD-07
                case 7: {
                    System.out.print("Production ID: ");
                    int prodId = sc.nextInt();

                    System.out.print("Mine ID: ");
                    int mineId = sc.nextInt();

                    if (mineDAO.findById(mineId) == null) {
                        System.out.println("Invalid Mine");
                        break;
                    }

                    System.out.print("Date: ");
                    LocalDate date = readDate(sc);

                    System.out.print("Tonnes: ");
                    double tonnes = sc.nextDouble();

                    System.out.print("Grade (0–100): ");
                    double grade = sc.nextDouble();

                    if (tonnes <= 0 || grade < 0 || grade > 100) {
                        System.out.println("Invalid production values");
                        break;
                    }

                    System.out.print("Shift ID (0 if none): ");
                    int shiftId = sc.nextInt();

                    ProductionLog log = new ProductionLog();
                    log.setProdId(prodId);
                    log.setMineId(mineId);
                    log.setLogDate(date);
                    log.setTonnes(tonnes);
                    log.setGrade(grade);
                    log.setShiftId(shiftId == 0 ? null : shiftId);

                    productionDAO.save(log);
                    System.out.println("Production recorded!");
                    break;
                }

                // UC-CRUD-08
                case 8: {
                    System.out.print("Usage ID: ");
                    int usageId = sc.nextInt();

                    System.out.print("Mine ID: ");
                    int mineId = sc.nextInt();

                    System.out.print("Equipment ID: ");
                    int eqId = sc.nextInt();

                    Equipment eq = equipmentDAO.findById(eqId);
                    if (eq == null || eq.getMineId() != mineId) {
                        System.out.println("Equipment does not belong to this mine");
                        break;
                    }

                    System.out.print("Date: ");
                    LocalDate date = readDate(sc);

                    System.out.print("Running Hours: ");
                    double hours = sc.nextDouble();

                    System.out.print("Breakdown (Y/N): ");
                    String breakdown = sc.next().toUpperCase();

                    System.out.print("Downtime Hours: ");
                    double downtime = sc.nextDouble();

                    EquipmentUsage usage = new EquipmentUsage();
                    usage.setUsageId(usageId);
                    usage.setMineId(mineId);
                    usage.setEquipmentId(eqId);
                    usage.setUsageDate(date);
                    usage.setRunningHours(hours);
                    usage.setBreakdown(breakdown);
                    usage.setDowntimeHours(downtime);

                    usageDAO.save(usage);
                    System.out.println("Equipment usage recorded!");
                    break;
                }

                // UC-CRUD-09
                case 9: {
                    System.out.print("Incident ID: ");
                    int id = sc.nextInt();

                    System.out.print("Mine ID: ");
                    int mineId = sc.nextInt();

                    System.out.print("Date: ");
                    LocalDate date = readDate(sc);

                    System.out.print("Type: ");
                    String type = sc.next();

                    System.out.print("Severity (1–5): ");
                    int severity = sc.nextInt();

                    if (severity < 1 || severity > 5) {
                        System.out.println("Invalid severity");
                        break;
                    }

                    System.out.print("Cost: ");
                    double cost = sc.nextDouble();

                    SafetyIncident si = new SafetyIncident();
                    si.setIncidentId(id);
                    si.setMineId(mineId);
                    si.setIncidentDate(date);
                    si.setType(type);
                    si.setSeverity(severity);
                    si.setCost(cost);
                    si.setStatus("OPEN");

                    incidentDAO.save(si);
                    System.out.println("Incident reported!");
                    break;
                }

                // UC-CRUD-10
//                case 10: {
//                    System.out.print("Incident ID: ");
//                    int id = sc.nextInt();
//
//                    SafetyIncident si = incidentDAO.findById(id);
//                    if (si == null) {
//                        System.out.println("Incident not found");
//                        break;
//                    }
//
//                    if ("CLOSED".equalsIgnoreCase(si.getStatus())) {
//                        System.out.println("Incident already closed");
//                        break;
//                    }
//
//                    si.setStatus("CLOSED");
//                    incidentDAO.update(si);
//
//                    System.out.println("Incident closed successfully!");
//                    break;
//                }

                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
