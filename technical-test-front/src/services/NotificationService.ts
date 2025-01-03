import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export interface Notification {
    message: string;
    type: 'SUCCESS' | 'INFO' | 'WARNING' | 'ERROR';
    timestamp: string;
}

export class NotificationService {
    private static instance: NotificationService;
    private client: Client;
    private subscribers: ((notification: Notification) => void)[] = [];

    private constructor() {
        this.client = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8082/ws'),
            onConnect: () => {
                console.log('Connected to WebSocket');
                this.subscribeToNotifications();
            },
            onStompError: (frame) => {
                console.error('STOMP error', frame);
            }
        });

        this.client.activate();
    }

    public static getInstance(): NotificationService {
        if (!NotificationService.instance) {
            NotificationService.instance = new NotificationService();
        }
        return NotificationService.instance;
    }

    private subscribeToNotifications() {
        this.client.subscribe('/topic/notifications', (message) => {
            const notification: Notification = JSON.parse(message.body);
            this.notifySubscribers(notification);
        });
    }

    public subscribe(callback: (notification: Notification) => void) {
        this.subscribers.push(callback);
        return () => {
            this.subscribers = this.subscribers.filter(sub => sub !== callback);
        };
    }

    private notifySubscribers(notification: Notification) {
        this.subscribers.forEach(callback => callback(notification));
    }

    public disconnect() {
        if (this.client.connected) {
            this.client.deactivate();
        }
    }
}
