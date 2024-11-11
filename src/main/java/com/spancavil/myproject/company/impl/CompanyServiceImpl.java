package com.spancavil.myproject.company.impl;
import com.spancavil.myproject.company.Company;
import com.spancavil.myproject.company.CompanyRepository;
import com.spancavil.myproject.company.CompanyService;
import com.spancavil.myproject.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company companyToUpdate, Long companyId) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isEmpty()) return false;
        else {
            Company company = companyOptional.get();
            company.setDescription(companyToUpdate.getDescription());
            company.setName(companyToUpdate.getName());
            company.setJobs(companyToUpdate.getJobs());
            companyRepository.save(company);
            return true;
        }
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
