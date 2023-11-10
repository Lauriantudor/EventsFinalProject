package ro.sda.eventsFinalProject.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.sda.eventsFinalProject.model.Token;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {


    @Query("""
             select t from Token t inner join User u on t.user.id = u.id 
             where u.id =:userId and (t.expired = false or t.revoked = false""")
    public List<Token> findAllValidTokensByUser(Integer userID);

    //todo see the cause of the exception :  Could not create query for public abstract java.util.List ro.sda.eventsFinalProject.model.TokenRepository.findAllValidTokensByUser(java.lang.Integer); Reason: Using named parameters for method public abstract java.util.List ro.sda.eventsFinalProject.model.TokenRepository.findAllValidTokensByUser(java.lang.Integer) but parameter 'Optional[userID]' not found in annotated query 'select t from Token t inner join User u on t.user.id = u.id
    //where u.id =:userId and (t.expired = false or t.revoked = false'
    public Optional<Token> findByToken(String token);

}
