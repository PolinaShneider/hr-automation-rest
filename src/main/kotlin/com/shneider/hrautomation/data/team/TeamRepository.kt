package com.shneider.hrautomation.data.team

import org.springframework.data.mongodb.repository.MongoRepository

interface TeamRepository : MongoRepository<TeamDTO, String>