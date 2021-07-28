package ConsomiTounsi.Service;


import ConsomiTounsi.entities.Claim;
import ConsomiTounsi.entities.ClaimType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ClaimManagerInterface {
    List<Claim> retrieveAllClaim();
    Claim addClaim(Claim C);
    void deleteClaim(Long id);
    void deleteClaim(String id);
    Claim updateClaim(Claim C);
    Claim FindClaim(Long id);
    Optional<Claim> FindClaim2(String id);
    List<Claim> FindBySubject(ClaimType subject);
    Optional<Claim> FindByDate(Date date);
    Claim updateClaimDecision(Claim C);
    //Claim updateStatus(Long id);
    //Claim addDecision(Claim C, String decision);
    //Optional<Claim> FindClaim3(Long idUser);
    Long CountTotalClaims() ;
    //void updateStatus();
    List<Claim> FindByStatus(String status);
    int claimsNumber (Long Bp);
    List<Claim> GetClaimsByLevelorder() ;
    List<Claim> GetClaimsByLevelorder2() ;
	List<Long> WorstProducts();
}
