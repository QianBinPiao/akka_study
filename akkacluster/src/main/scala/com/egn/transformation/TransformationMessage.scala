package com.egn.transformation

/**
 * Created by ypiao on 8/4/15.
 */
case class TransformationJob(text: String)
case class TransformationResult(text: String)
case class JobFailed(reason: String, job: TransformationJob)
case object BackendRegistration
