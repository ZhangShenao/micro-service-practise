package william.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import william.order.service.FlowControlService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ZhangShenao
 * @date 2023/8/24 9:55 AM
 * @description: Sentinel流控规则API
 */
@RestController
@RequestMapping("/flow_control")
public class FlowControlController {
    
    @Resource
    private FlowControlService flowControlService;
    
    /**
     * 阈值类型=QPS 流控模式=直接 流控效果=快速失败
     */
    @GetMapping("/qps/direct/fail_fast")
    public String qpsDirectFailFast() {
        return "{阈值类型=QPS,流控模式=直接,流控效果=快速失败}";
    }
    
    /**
     * 阈值类型=并发线程数 流控模式=直接 流控效果=快速失败
     */
    @GetMapping("/thread/direct/fail_fast")
    public String threadDirectFailFast() throws InterruptedException {
        //模拟耗时任务,长时间占用线程
        Thread.sleep(1000L);
        return "{阈值类型=并发线程数,流控模式=直接,流控效果=快速失败}";
    }
    
    /**
     * 阈值类型=QPS 流控模式=关联 流控效果=快速失败
     * <p>实现的效果：当对/qps/associated/fail_fast/b资源的访问达到阈值时,就限流/qps/associated/fail_fast/a</p>
     */
    @GetMapping("/qps/related/fail_fast/a")
    public String qpsRelatedFailFastA() {
        return "{阈值类型=QPS,流控模式=关联,流控效果=快速失败}";
    }
    
    /**
     * 阈值类型=QPS 流控模式=关联 流控效果=快速失败
     */
    @GetMapping("/qps/related/fail_fast/b")
    public String qpsRelatedFailFastB() {
        return "{阈值类型=QPS,流控模式=关联,流控效果=快速失败}";
    }
    
    /**
     * 阈值类型=QPS 流控模式=链路 流控效果=快速失败
     * <p>实现的效果：当对shared-resource资源的访问达到阈值时,就限流/qps/trace/fail_fast/a</p>
     */
    @GetMapping("/qps/trace/fail_fast/a")
    public String qpsTraceFailFastA() {
        flowControlService.visitSharedResource();
        return "{阈值类型=QPS,流控模式=链路,流控效果=快速失败}";
    }
    
    /**
     * 阈值类型=QPS 流控模式=链路 流控效果=快速失败
     * <p>/qps/trace/fail_fast/b没有设置链路限流模式,因此即使对shared-resource资源的访问达到阈值,也不会被限流</p>
     */
    @GetMapping("/qps/trace/fail_fast/b")
    public String qpsTraceFailFastB() {
        flowControlService.visitSharedResource();
        return "{阈值类型=QPS,流控模式=链路,流控效果=快速失败}";
    }
    
    /**
     * 阈值类型=QPS 流控模式=直接 流控效果=Warm Up
     */
    @GetMapping("/qps/direct/warmup")
    public String qpsDirectWarmUp() {
        return "{阈值类型=QPS,流控模式=直接,流控效果=Warm Up}";
    }
    
    /**
     * 阈值类型=QPS 流控模式=直接 流控效果=排队等待
     */
    @GetMapping("/qps/direct/queued")
    public String qpsDirectQueued() {
        System.out.println("执行线程: " + Thread.currentThread().getName() + ", 执行时间: " + new Date());
        return "{阈值类型=QPS,流控模式=直接,流控效果=排队等待}";
    }
}
