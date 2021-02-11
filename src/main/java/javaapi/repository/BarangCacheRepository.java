package javaapi.repository;

import javaapi.model.Barang;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BarangCacheRepository {

    public static final String KEY = "BARANG";
    private RedisTemplate<String,Barang> redisTemplate;
    private HashOperations hashOperations;

    public BarangCacheRepository(RedisTemplate<String, Barang> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    /*Getting all Barangs from tSable*/
    public List<Barang> getAllBarangs(){
        Map<Integer,Barang> items =  hashOperations.entries(KEY);
        List<Barang> list = new ArrayList<Barang>(items.values());
        return list;
    }
    /*Getting a specific barang by barang id from table*/
    public Barang getBarang(int barangId){
        return (Barang) hashOperations.get(KEY,barangId);
    }

    /*Adding an barang into redis database*/
    public void addBarang(Barang barang){
        hashOperations.put(KEY,barang.getId(),barang);
    }
    /*delete an barang from database*/
    public void deleteBarang(int id){
        hashOperations.delete(KEY,id);
    }

    /*update an barang from database*/
    public void updateBarang(Barang barang){
        addBarang(barang);
    }
}
