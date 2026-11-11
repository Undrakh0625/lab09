# Lab09 JaCoCo Код хамралт

Нэр: Undrakh  
Оюутны код: b252270135

## Орчны хувилбарын бүтэн гаралт

`java -version`:

```text
java version "25.0.2" 2026-01-20 LTS
Java(TM) SE Runtime Environment (build 25.0.2+10-LTS-69)
Java HotSpot(TM) 64-Bit Server VM (build 25.0.2+10-LTS-69, mixed mode, sharing)
```

`mvn -version`:

```text
Apache Maven 3.10.0 (c43a36b8d67be7e0805a411bc0898af1a51f5472)
Maven home: /Users/undrakherdenetsogt/Documents/Codex/2026-11-10/files-mentioned-by-the-user-lab09/work/tools/apache-maven-3.10.0
Java version: 25.0.2, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-25.jdk/Contents/Home
Default locale: en_MN, platform encoding: UTF-8, time zone: Asia/Ulaanbaatar
OS name: "mac os x", version: "26.2", arch: "aarch64", family: "mac"
```

Төслийн босго, тэтгэлгийн нөхцөлийг хавсаргасан starter төслийн `ScholarshipCalculator.java`-аас авсан. Starter-ийн бусад файлыг `pom.xml` болон эх кодын хамт хадгалав.

## A — Эхний happy-path хэмжилт

Эхний таван happy-path тест `results/mvn-verify-A.txt`-д амжилттай. `results/jacoco-A.csv`-ийн ScholarshipCalculator мөрөөр Line хамралт 15/25 = **60.0%**, Branch хамралт 20/41 = **48.8%** байв. Шар салаанууд нь онооны ангиллын дараалсан нөхцөл болон тэтгэлгийн GPA/кредитийн хос шалгалтын дутуу салаанууд байлаа. Улаан мөрүүдэд D/F дүн, GPA-ийн алдааны нөхцөлүүд, сахилгын зөрчил, доод шатны тэтгэлгийн буцах замууд орсон.

## B — CFG ба бүрэн салаалалтын хамралт

[ScholarshipCalculator.scholarship() CFG](cfg.jpg) диаграммд зангилаа, ирмэг бүрийг болон тухайн ирмэгээр явсан бодит тестийн методын нэрийг тэмдэглэв. Бүрэн хамралтад нийт **20 тест** шаардсан (`results/mvn-verify-B.txt`). `results/jacoco-B.csv`-д `BRANCH_MISSED = 0`, `LINE_MISSED = 0`; Branch нь 41/41, Line нь 25/25 болсон.

## C — Давталтын зааг

Давталтын заагийн тестүүд:

- 0 удаа: `gpaZeroIterationEmptyArrayThrows` — хоосон массив; `gpaNullInputThrows` — null оролт.
- 1 удаа: `gpaOneIteration` — `[95]` массивын GPA 4.0.
- 2+ удаа: `gpaMultipleIterations` — `[95, 85, 75, 65]` массивын GPA 2.5.

Эдгээр тест давталтын 0, 1, олон гүйцэтгэлийн үр дүнг шалгана. Branch болон Line аль хэдийн 100% байсан ч хоосон/ганц утгатай оролтын функциональ алдааг илрүүлэхэд хэрэгтэй.

## D — 100% хамралтын дараах заагийн алдаа

Заагийн шалгалтаар `letterGradeAt60ReturnsD` тест унасан: `expected: <D> but was: <F>`. Уналтын бүтэн гаралт `results/mvn-test-D-fail.txt`-д бий. Алдаа нь `score > 60` харьцуулалтад байв; starter-ийн шаардлагаар 60 оноо D байх тул `score >= 60` болгож зассан. Засварын дараа `mvn verify` 31 тестийг амжилттай ажиллуулж, `results/jacoco-final.csv`-д `BRANCH_MISSED = 0`, `LINE_MISSED = 0` гэж баталгаажсан.

Branch хамралт нь кодын салаануудын үр дүн ажилласан эсэхийг хэмждэг. `score > 60` болон `score >= 60` нь заагийн бус утгуудад ижил салааллын зураглал үүсгэж болно. B шатанд яг 60 оноог шалгаагүй тул бүх салаа хамрагдсан ч операторын алдаа илрээгүй. `letterGradeAt60ReturnsD` заагийн тест энэ алдааг барьсан. Тиймээс 100% хамралт нь кодын замууд ажилласныг харуулдаг боловч шаардлагын зөв хэрэгжилтийг дангаараа баталдаггүй.

## AI туслахын хэрэглээ

Codex AI туслахыг starter-ийг шалгаж, JUnit тестүүд нэмэх, JaCoCo тайлан гаргах, CFG диаграмм болон README боловсруулахад ашиглав.
