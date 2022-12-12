package com.jpa.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.dao.RentalDao;
import com.jpa.entity.ClientEntity;
import com.jpa.entity.EmployeeEntity;
import com.jpa.entity.RentalNewEntity;
import com.jpa.entity.SalariesEntity;
import com.jpa.entity.relationship.*;
import com.jpa.enumerator.Gender;
import com.jpa.enumerator.Status;
import com.jpa.repository.*;
import com.jpa.service.ActorService;
import com.jpa.service.ApiService;
import com.jpa.service.CityService;
import com.jpa.service.CountryService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ApiServiceImpl implements ApiService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("customObjectMapper")
    private ObjectMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private RentalDao rentalDao;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RentalNewRepository rentalNewRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SalariesRepository salariesRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostDetailRepository postDetailRepository;

    @Override
    public <T> List<T> getRentalMovies(String title) {
//        return (List<T>) rentalRepository.getRentalMoviesProjection(title);

//        return (List<T>) rentalRepository.getRentalMoviesTuple(title).stream()
//                .map(s -> {
//                    Timestamp rental_date = (Timestamp) s.get("rental_date");
//                    return new MovieRentalDto().builder()
//                                    .title((String) s.get("title"))
//                                    .rentalDate(rental_date.toLocalDateTime())
//                                    .build();
//                })
//                .collect(Collectors.toList());

//        return (List<T>) rentalRepository.getRentalMoviesDto(title);

        return (List<T>) rentalDao.getRentalMovies(title);

//        return (List<T>) countryRepository.findAllById(Collections.singleton(999));
    }

    @Override
    @Transactional
    public <T> T testJpaSave() {
//        CountryEntity entity = countryRepository.findById(1).get();
        ActorEntity entity = actorRepository.findById(200).get();
//        ActorEntity entity = rentalDao.findActorWithLock(200);
//        ActorEntity entity = actorRepository.findLockPessimistic(200);
//        return (T) entity;
//        List<ActorEntity> data = rentalDao.findActorForJobQueueSkipLock();
//        List<ActorEntity> data = actorRepository.findTop2ByLastName();
//        ActorEntity entity = actorRepository.findLockOptimistic(200);
        return (T) entity;
//        entity.setLastName(entity.getLastName() + "_" + entity.getFirstName());
//        actorRepository.save(entity);
//        return (T) entity;

//        long start = System.currentTimeMillis();
//        System.out.println("start: " + start +  "********************************");
//
//        long end = System.currentTimeMillis();
//        System.out.println("Total time: " + (end - start));
//        System.out.println("end: " + end + " ********************************");
//        return (T) "success";
    }

    @Override
    @Transactional
    public <T> T handleTransactional() {
        String postfix = " 3";
//        ActorEntity entity = actorRepository.findById(200).get();
//        ActorEntity entity = rentalDao.findActorWithLock(200);
//        ActorEntity entity = actorRepository.findLockOptimistic();
        ActorEntity entity = actorRepository.findLockPessimistic(200);
//        List<ActorEntity> data = actorRepository.findTop2ByLastName();
//        List<ActorEntity> data = rentalDao.findActorForJobQueueSkipLock();
//        entity.setFirstName("THORA" + postfix);
//        actorRepository.save(entity);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        actorService.saveActor("THORA" + postfix);
//        cityService.saveCity("Ziguinchor" + postfix);
//        int a = 1/0;
        return (T) entity;
    }

    @Override
    @Transactional
    public void handleTransactionalReplica() {
        String postfix = " 2";
        try {
            cityService.saveCity("Ziguinchor" + postfix);
            int a = 1/0;
        }catch (ArithmeticException e) {
            actorService.saveActorRollbackFor("THORA" + postfix);
            throw e;
        }
    }

    @Override
    public <T> T handleLargeData() {
        List<SalariesEntity> salaryList = salariesRepository.findAll();
        List<EmployeeEntity> employeeData = employeeRepository.findAll();
        List<EmployeeEntity> result = employeeData.subList(0, 10);
        result.add(EmployeeEntity.builder()
                .empNo(9911)
                .build());

        long start = System.currentTimeMillis();
        System.out.println("start: " + start +  "********************************");

        Map<Integer, Integer> salaryMap = salaryList.stream()
                .filter(s -> s.getSalary() != null)
                .collect(Collectors.groupingBy(
                        SalariesEntity::getEmpNo,
                        Collectors.summingInt(SalariesEntity::getSalary)));

//        val salaryMap: Map<Int, Int> = salaryList
//            .filter { it.salary != null }
//            .groupBy({ it.empNo }, { it.salary!! })
//            .mapValues { it.value.sumOf { it } }

        for (EmployeeEntity s : result) {
            s.setSalary(salaryMap.get(s.getEmpNo()));
        }

        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end - start));
        System.out.println("end: " + end + " ********************************");
        return (T) result;
    }

    @Override
    public Page<EmployeeEntity> getEmployeeList(EmployeeEntity dto) {
        Pageable pageable = PageRequest.of(dto.getCurrentPage(), dto.getPageSize());
        return employeeRepository.getEmployeeList(dto, pageable);
    }

    @Override
    @Transactional
    public void importExcel(MultipartFile file) throws Exception {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row row;
        List<RentalNewEntity> data = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            RentalNewEntity entity = RentalNewEntity.builder()
                    .rentalDate(row.getCell(1).getLocalDateTimeCellValue())
                    .inventoryId((int) row.getCell(2).getNumericCellValue())
                    .customerId((int) row.getCell(3).getNumericCellValue())
                    .returnDate(LocalDateTime.now())
                    .staffId((int) row.getCell(5).getNumericCellValue())
                    .lastUpdate(row.getCell(6).getLocalDateTimeCellValue())
                    .build();
            data.add(entity);
        }
        rentalNewRepository.saveAll(data);
//        entityManager.unwrap(Session.class).setJdbcBatchSize(500);
//        data.forEach(s -> {
//            entityManager.persist(s);
//        });
    }

}
