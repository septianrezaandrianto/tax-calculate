# tax-calculate

## Stack
1. Java JDK 21
2. Spring Boot 3.2.0
3. Maven 3.9.1

## Libraries :
1. Lombok <br>
2. Spring Data JPA <br>
3. H2 Database <br>
4. Lombok <br>
5. Gson <br>
6. Spring Boot Dev Tools <br>
7. openapi (swagger3) <br>.
8. jUnit5.

## Rules of Game:
1. Import the postman collection from postman-collection folder in this project to your postman. <br>
2. mvn spring-boot:run : Use this script for running this project using CMD / Terminal. <br>
3. mvn spring-boot:test : Use this script for running unit testing. <br>
4. Open http://localhost:8080/h2-console in your browser for checking Memory database. <br>
5. Open http://localhost:8080/swagger-ui/index.html in your browser for opening swagger. <br>
6. Open http://localhost:8080/api-docs in your browser for opening API docs. <br>
7. When Project running, the service will automatically save master data into H2 database at tables (tax_rate_master, tax_relief_master, and tax_relief_master_detail) <br><br>

7a. If you want to see data tax rate master you can hit endpoint /taxReliefMaster/getDataByPage<br>
    curl --location 'localhost:8080/taxRateMaster/getDataByPage' \
--header 'Content-Type: application/json' \
--data '{
    "filter" : "",
    "pageSize" : "3",
    "pageNumber" : "0"
}'

Example Response : {
    "responseCode": 200,
    "responseMessage": "Successful",
    "data": [
        {
            "id": 1,
            "category": "A",
            "chargeableIncomeMin": "RM0",
            "calculationMin": "RM0",
            "caluclationMax": "RM5,000",
            "rate": 0,
            "taxMin": "RM0",
            "taxMax": "RM0"
        },
        {
            "id": 2,
            "category": "B",
            "chargeableIncomeMin": "RM5,001",
            "calculationMin": "RM5,001",
            "caluclationMax": "RM20,000",
            "rate": 1,
            "taxMin": "RM0",
            "taxMax": "RM150"
        },
        {
            "id": 3,
            "category": "C",
            "chargeableIncomeMin": "RM20,001",
            "calculationMin": "RM20,001",
            "caluclationMax": "RM35,000",
            "rate": 3,
            "taxMin": "RM150",
            "taxMax": "RM450"
        }
    ],
    "pageNumber": 0,
    "pageSize": 3,
    "totalData": 10
}



7b. If you want to see data tax relief master you can hit endpoint /taxReliefMaster/getDataByPage<br>
cURL : curl --location 'localhost:8080/taxReliefMaster/getDataByPage' \
--header 'Content-Type: application/json' \
--data '{
    "pageSize" : "3",
    "pageNumber" : "0"

}' 

Example Response : {
    "responseCode": 200,
    "responseMessage": "Successful",
    "data": [
        {
            "id": 1,
            "number": "1",
            "detailList": [
                {
                    "id": 1,
                    "individualReliefType": "Individual and dependent relatives",
                    "amount": "RM9,000",
                    "information": null
                }
            ]
        },
        {
            "id": 2,
            "number": "2",
            "detailList": [
                {
                    "id": 2,
                    "individualReliefType": "Medical treatment, special needs and carer expenses for parents (Medical condition certified by medical practitioner)",
                    "amount": "RM8,000",
                    "information": "(Restricted)"
                }
            ]
        },
        {
            "id": 3,
            "number": "3",
            "detailList": [
                {
                    "id": 3,
                    "individualReliefType": "Purchase of basic supporting equipment for disabled self, spouse, child or parent",
                    "amount": "RM6,000",
                    "information": "(Restricted)"
                }
            ]
        }
    ],
    "pageNumber": 0,
    "pageSize": 3,
    "totalData": 23
}

7c. If you want to se calculate tax, you can hit endpoint /tax/calculateTax<br>
curl --location 'localhost:8080/tax/calculateTax' \
--header 'Content-Type: application/json' \
--data '{
    "annualIncome" : "5001"
}'

Example Response : {
    "responseCode": 200,
    "responseMessage": "Successful",
    "data": {
        "annualIncome": "RM5,001",
        "taxClaimOptions": [
            {
                "id": 9,
                "number": "9",
                "detailList": [
                    {
                        "id": 9,
                        "individualReliefType": "Lifestyle – Expenses for the use / benefit of self, spouse or child in respect of:\n\n    Purchase or subscription of books / journals / magazines / newspapers / other similar publications (Not banned reading materials)\n    Purchase of personal computer, smartphone or tablet (Not for business use)\n    Purchase of sports equipment for sports activity defined under the Sports Development Act 1997 and payment of gym membership\n    Payment of monthly bill for internet subscription (Under own name)",
                        "amount": "RM2,500",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 10,
                "number": "10",
                "detailList": [
                    {
                        "id": 10,
                        "individualReliefType": "Lifestyle – Additional relief for the use / benefit of self, spouse or child in respect of:\n\n    Purchase of sports equipment for any sports activity as defined under the Sports Development Act 1997\n    Payment of rental or entrance fee to any sports facility\n    Payment of registration fee for any sports competition where the organizer is approved and licensed by the Commissioner of Sports under the Sports Development Act 1997",
                        "amount": "RM500",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 11,
                "number": "11",
                "detailList": [
                    {
                        "id": 11,
                        "individualReliefType": "Purchase of breastfeeding equipment for own use for a child aged 2 years and below (Deduction allowed once in every TWO (2) years of assessment)",
                        "amount": "RM1,000",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 12,
                "number": "12",
                "detailList": [
                    {
                        "id": 12,
                        "individualReliefType": "Child care fees to a registered child care centre / kindergarten for a child aged 6 years and below",
                        "amount": "RM3,000",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 14,
                "number": "14",
                "detailList": [
                    {
                        "id": 14,
                        "individualReliefType": "Husband / wife / payment of alimony to former wife",
                        "amount": "RM4,000",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 16,
                "number": "16a",
                "detailList": [
                    {
                        "id": 16,
                        "individualReliefType": "Each unmarried child and under the age of 18 years old",
                        "amount": "RM2,000",
                        "information": null
                    }
                ]
            },
            {
                "id": 17,
                "number": "16b",
                "detailList": [
                    {
                        "id": 17,
                        "individualReliefType": "Each unmarried child of 18 years and above who is receiving full-time education (\"A-Level\", certificate, matriculation or preparatory courses).",
                        "amount": "RM2,000",
                        "information": null
                    }
                ]
            },
            {
                "id": 20,
                "number": "18",
                "detailList": [
                    {
                        "id": 22,
                        "individualReliefType": "Deferred Annuity and Private Retirement Scheme (PRS)",
                        "amount": "RM3,000",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 21,
                "number": "19",
                "detailList": [
                    {
                        "id": 23,
                        "individualReliefType": "Education and medical insurance",
                        "amount": "RM3,000",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 22,
                "number": "20",
                "detailList": [
                    {
                        "id": 24,
                        "individualReliefType": "Contribution to the Social Security Organization (SOCSO)",
                        "amount": "RM350",
                        "information": "(Restricted)"
                    }
                ]
            },
            {
                "id": 23,
                "number": "21",
                "detailList": [
                    {
                        "id": 25,
                        "individualReliefType": "Expenses on charging facilities for Electric Vehicle (Not for business use)",
                        "amount": "RM2,500",
                        "information": "(Restricted)"
                    }
                ]
            }
        ],
        "rate": "1 %",
        "tax": "RM0",
        "taxDeduction": "RM50",
        "takeHomePay": "RM4,950"
    }
}

