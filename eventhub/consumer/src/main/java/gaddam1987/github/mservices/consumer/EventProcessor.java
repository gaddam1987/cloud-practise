package gaddam1987.github.mservices.consumer;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;

public class EventProcessor implements IEventProcessor {

    private int checkpointBatchingCount = 0;

    @Override
    public void onOpen(PartitionContext context) throws Exception {
        System.out.println("SAMPLE: Partition " + context.getPartitionId() + " is opening");
    }

    @Override
    public void onClose(PartitionContext context, CloseReason reason) throws Exception {
        System.out.println("SAMPLE: Partition " + context.getPartitionId() + " is closing for reason " + reason.toString());
    }

    @Override
    public void onEvents(PartitionContext context, Iterable<EventData> messages) throws Exception {
        //System.out.println("SAMPLE: Partition " + context.getPartitionId() + " got message batch");
        int messageCount = 0;
        for (EventData data : messages) {
            System.out.println("SAMPLE (" + context.getPartitionId() + "," + data.getSystemProperties().getOffset() + "," + data.getSystemProperties().getSequenceNumber() + "): " + new String(data.getBody(), "UTF8"));
            messageCount++;

            // Checkpointing persists the current position in the event stream for this partition and means that the next
            // time any host opens an event processor on this event hub+consumer group+partition combination, it will start
            // receiving at the event after this one. Checkpointing is usually not a fast operation, so there is a tradeoff
            // between checkpointing frequently (to minimize the number of events that will be reprocessed after a crash, or
            // if the partition lease is stolen) and checkpointing infrequently (to reduce the impact on event processing
            // performance). Checkpointing every five events is an arbitrary choice for this sample.
            //this.checkpointBatchingCount++;
            //if ((checkpointBatchingCount % 5) == 0) {
            context.checkpoint(data);
            //}
            //System.out.println("SAMPLE: Partition " + context.getPartitionId() + " batch size was " + messageCount + " for host " + context.getOwner() + "with offset " + data.getSystemProperties().getOffset());
        }
    }

    @Override
    public void onError(PartitionContext context, Throwable error) {
        System.out.println("SAMPLE: Partition " + context.getPartitionId() + " onError: " + error.toString());
    }
}
