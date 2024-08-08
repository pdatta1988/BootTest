package fi.test.boot.BootTest.repository;


import fi.test.boot.BootTest.model.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ResourceRepository extends CrudRepository<Resource, Integer>{

   /* @Query("UPDATE Resource r set r.resourceName=:resourceName WHERE r.resourceId=:resourceId")
    public int updateResource(@Param("resourceName")String resourceName ,@Param("resourceId") int resourceId);*/



}
