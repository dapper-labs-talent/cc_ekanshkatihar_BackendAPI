How long did this assignment take?
> Around 5.5 hour and I wasn't able to allocate all the time at once.

 It has been long since I have implemented security from scratch so I have to do some reading on JWT and framework of my choice ( Spring security)

What was the hardest part?
> Having relatively lesser exposure on implemening security, it was definitely a challenge to make a working model withing the time constraint. 

Did you learn anything new?
>I got to refresh my knowledge on JWT and how Spring implements Security.

Is there anything you would have liked to implement but didn't have the time to?
>I have implemented exception handling but it should have been more refined: 
> 1. Example, log proper exception caught on the server side and return a more generic exception to the end user. 
> 2. I didn't get time to write JUnit and Mock test cases.
> 3. I would also like to check what all vulnerabilities exist when using JWT token and would like to test my API against that and fix that.  

What are the security holes (if any) in your system? If there are any, how would you fix them?
>`Secret` is part of the resource file . This could reveal production level secret of the application to lot of developers. I would like either: 
> 1. it to be available to priviliged users and let support introduce it at the time of deployment.
> 2. Or secret can also be present in the prod database so application can pick it up from there.  

Do you feel that your skills were well tested?
> Yes, Given a problem with mix of Known and Unknown domain ( security ) how fast I can solve it .