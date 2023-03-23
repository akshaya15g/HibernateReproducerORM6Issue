# HibernateReproducerORM6Issue
This is a test case using hibernate test template to demonstrate the issue regarding binding

Import the maven project and run the test case under ORMStandaloneTestCase.java

Test name is hhh123Test()

The output of the query is not printed. It leads to following exception:

2023-03-23 11:02:46 WARN  SqlExceptionHelper:133 - SQL Error: 23506, SQLState: 23506
2023-03-23 11:02:46 ERROR SqlExceptionHelper:138 - Referential integrity constraint violation: "FK658966DPRJV6Q0IX1LTY33LVQ: PUBLIC.OT_TXN_PARTY FOREIGN KEY(SEQ_NO, REF_NO) REFERENCES PUBLIC.OT_GR_TXN_DETAILS(REF_NO, SEQ_NO) ('001', 'E313100007923221937')"; SQL statement:
insert into OT_TXN_PARTY (PARTY_ID, PARTY_CODE, REF_NO, SEQ_NO) values (?, ?, ?, ?) [23506-214]
2023-03-23 11:02:46 INFO  AbstractBatchImpl:213 - HHH000010: On release of batch it still contained JDBC statements

jakarta.persistence.PersistenceException: Converting `org.hibernate.exception.ConstraintViolationException` to JPA `PersistenceException` : could not execute statement

# This is due to issue in hibernate regarding binding the columns in @OneToMany join in InProcessGuaranteeEntity.java

Code:

@OneToMany(fetch = EAGER,cascade=CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "REF_NO", referencedColumnName = "REF_NO"),
            @JoinColumn(name = "SEQ_NO", referencedColumnName = "SEQ_NO")
    })
    public Set<InProcessPartyEntity> getParties() {
        return parties;
    }
    
    
  # If the above code is changed to following (i.e. reverse the referenced columns which is logically incorrect), it works fine
  
  @OneToMany(fetch = EAGER,cascade=CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "REF_NO", referencedColumnName = "SEQ_NO"),
            @JoinColumn(name = "SEQ_NO", referencedColumnName = "REF_NO")
    })
    public Set<InProcessPartyEntity> getParties() {
        return parties;
    }
