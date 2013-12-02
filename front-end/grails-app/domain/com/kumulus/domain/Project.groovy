package com.kumulus.domain

class Project {

	String projectName
	String status

	static hasMany = [applicationParameters: ApplicationParameter,
	                  attendances: Attendance,
	                  nodes: Nodes]

	static mapping = {
		id column: "project_id"
		version false
	}

	static constraints = {
		projectName nullable: true, maxSize: 50, unique: true
		status nullable: true, maxSize: 10
	}
}
